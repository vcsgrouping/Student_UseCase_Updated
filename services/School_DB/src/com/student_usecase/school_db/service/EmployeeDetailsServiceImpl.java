/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.data.annotations.EntityService;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.student_usecase.school_db.EmployeeDetails;
import com.student_usecase.school_db.UserLogin;


/**
 * ServiceImpl object for domain model class EmployeeDetails.
 *
 * @see EmployeeDetails
 */
@Service("School_DB.EmployeeDetailsService")
@Validated
@EntityService(entityClass = EmployeeDetails.class, serviceId = "School_DB")
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDetailsServiceImpl.class);


    @Autowired
    @Qualifier("School_DB.EmployeeDetailsDao")
    private WMGenericDao<EmployeeDetails, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<EmployeeDetails, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
    public EmployeeDetails create(EmployeeDetails employeeDetails) {
        LOGGER.debug("Creating a new EmployeeDetails with information: {}", employeeDetails);

        EmployeeDetails employeeDetailsCreated = this.wmGenericDao.create(employeeDetails);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(employeeDetailsCreated);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public EmployeeDetails getById(Integer employeedetailsId) {
        LOGGER.debug("Finding EmployeeDetails by id: {}", employeedetailsId);
        return this.wmGenericDao.findById(employeedetailsId);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public EmployeeDetails findById(Integer employeedetailsId) {
        LOGGER.debug("Finding EmployeeDetails by id: {}", employeedetailsId);
        try {
            return this.wmGenericDao.findById(employeedetailsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No EmployeeDetails found with id: {}", employeedetailsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public List<EmployeeDetails> findByMultipleIds(List<Integer> employeedetailsIds, boolean orderedReturn) {
        LOGGER.debug("Finding EmployeeDetails by ids: {}", employeedetailsIds);

        return this.wmGenericDao.findByMultipleIds(employeedetailsIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "School_DBTransactionManager")
    @Override
    public EmployeeDetails update(EmployeeDetails employeeDetails) {
        LOGGER.debug("Updating EmployeeDetails with information: {}", employeeDetails);

        List<EmployeeDetails> employeeDetailsesForManagerId = employeeDetails.getEmployeeDetailsesForManagerId();
        UserLogin userLogin = employeeDetails.getUserLogin();
        if(employeeDetailsesForManagerId != null && Hibernate.isInitialized(employeeDetailsesForManagerId)) {
            employeeDetailsesForManagerId.forEach(_employeeDetails -> _employeeDetails.setEmployeeDetailsByManagerId(employeeDetails));
        }
        if(userLogin != null && Hibernate.isInitialized(userLogin)) {
            userLogin.setEmployeeDetails(employeeDetails);
        }

        this.wmGenericDao.update(employeeDetails);
        this.wmGenericDao.refresh(employeeDetails);

        return employeeDetails;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
    public EmployeeDetails partialUpdate(Integer employeedetailsId, Map<String, Object>employeeDetailsPatch) {
        LOGGER.debug("Partially Updating the EmployeeDetails with id: {}", employeedetailsId);

        EmployeeDetails employeeDetails = getById(employeedetailsId);

        try {
            ObjectReader employeeDetailsReader = this.objectMapper.reader().forType(EmployeeDetails.class).withValueToUpdate(employeeDetails);
            employeeDetails = employeeDetailsReader.readValue(this.objectMapper.writeValueAsString(employeeDetailsPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", employeeDetailsPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        employeeDetails = update(employeeDetails);

        return employeeDetails;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
    public EmployeeDetails delete(Integer employeedetailsId) {
        LOGGER.debug("Deleting EmployeeDetails with id: {}", employeedetailsId);
        EmployeeDetails deleted = this.wmGenericDao.findById(employeedetailsId);
        if (deleted == null) {
            LOGGER.debug("No EmployeeDetails found with id: {}", employeedetailsId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), EmployeeDetails.class.getSimpleName(), employeedetailsId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
    public void delete(EmployeeDetails employeeDetails) {
        LOGGER.debug("Deleting EmployeeDetails with {}", employeeDetails);
        this.wmGenericDao.delete(employeeDetails);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<EmployeeDetails> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all EmployeeDetails");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<EmployeeDetails> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all EmployeeDetails");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service School_DB for table EmployeeDetails to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service School_DB for table EmployeeDetails to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<EmployeeDetails> findAssociatedEmployeeDetailsesForManagerId(Integer empId, Pageable pageable) {
        LOGGER.debug("Fetching all associated employeeDetailsesForManagerId");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("employeeDetailsByManagerId.empId = '" + empId + "'");

        return findAll(queryBuilder.toString(), pageable);
    }


}