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
import org.springframework.context.annotation.Lazy;
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

import com.student_usecase.school_db.AcademicTestSubjects;
import com.student_usecase.school_db.AcademicTestSubjectsId;
import com.student_usecase.school_db.TestConducted;


/**
 * ServiceImpl object for domain model class AcademicTestSubjects.
 *
 * @see AcademicTestSubjects
 */
@Service("School_DB.AcademicTestSubjectsService")
@Validated
@EntityService(entityClass = AcademicTestSubjects.class, serviceId = "School_DB")
public class AcademicTestSubjectsServiceImpl implements AcademicTestSubjectsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcademicTestSubjectsServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("School_DB.TestConductedService")
    private TestConductedService testConductedService;

    @Autowired
    @Qualifier("School_DB.AcademicTestSubjectsDao")
    private WMGenericDao<AcademicTestSubjects, AcademicTestSubjectsId> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<AcademicTestSubjects, AcademicTestSubjectsId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
    public AcademicTestSubjects create(AcademicTestSubjects academicTestSubjects) {
        LOGGER.debug("Creating a new AcademicTestSubjects with information: {}", academicTestSubjects);

        List<TestConducted> testConducteds = academicTestSubjects.getTestConducteds();
        if(testConducteds != null && Hibernate.isInitialized(testConducteds)) {
            testConducteds.forEach(_testConducted -> _testConducted.setAcademicTestSubjects(academicTestSubjects));
        }

        AcademicTestSubjects academicTestSubjectsCreated = this.wmGenericDao.create(academicTestSubjects);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(academicTestSubjectsCreated);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public AcademicTestSubjects getById(AcademicTestSubjectsId academictestsubjectsId) {
        LOGGER.debug("Finding AcademicTestSubjects by id: {}", academictestsubjectsId);
        return this.wmGenericDao.findById(academictestsubjectsId);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public AcademicTestSubjects findById(AcademicTestSubjectsId academictestsubjectsId) {
        LOGGER.debug("Finding AcademicTestSubjects by id: {}", academictestsubjectsId);
        try {
            return this.wmGenericDao.findById(academictestsubjectsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No AcademicTestSubjects found with id: {}", academictestsubjectsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public List<AcademicTestSubjects> findByMultipleIds(List<AcademicTestSubjectsId> academictestsubjectsIds, boolean orderedReturn) {
        LOGGER.debug("Finding AcademicTestSubjects by ids: {}", academictestsubjectsIds);

        return this.wmGenericDao.findByMultipleIds(academictestsubjectsIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "School_DBTransactionManager")
    @Override
    public AcademicTestSubjects update(AcademicTestSubjects academicTestSubjects) {
        LOGGER.debug("Updating AcademicTestSubjects with information: {}", academicTestSubjects);

        List<TestConducted> testConducteds = academicTestSubjects.getTestConducteds();
        if(testConducteds != null && Hibernate.isInitialized(testConducteds)) {
            testConducteds.forEach(_testConducted -> _testConducted.setAcademicTestSubjects(academicTestSubjects));
        }

        this.wmGenericDao.update(academicTestSubjects);
        this.wmGenericDao.refresh(academicTestSubjects);

        return academicTestSubjects;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
    public AcademicTestSubjects partialUpdate(AcademicTestSubjectsId academictestsubjectsId, Map<String, Object>academicTestSubjectsPatch) {
        LOGGER.debug("Partially Updating the AcademicTestSubjects with id: {}", academictestsubjectsId);

        AcademicTestSubjects academicTestSubjects = getById(academictestsubjectsId);

        try {
            ObjectReader academicTestSubjectsReader = this.objectMapper.reader().forType(AcademicTestSubjects.class).withValueToUpdate(academicTestSubjects);
            academicTestSubjects = academicTestSubjectsReader.readValue(this.objectMapper.writeValueAsString(academicTestSubjectsPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", academicTestSubjectsPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        academicTestSubjects = update(academicTestSubjects);

        return academicTestSubjects;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
    public AcademicTestSubjects delete(AcademicTestSubjectsId academictestsubjectsId) {
        LOGGER.debug("Deleting AcademicTestSubjects with id: {}", academictestsubjectsId);
        AcademicTestSubjects deleted = this.wmGenericDao.findById(academictestsubjectsId);
        if (deleted == null) {
            LOGGER.debug("No AcademicTestSubjects found with id: {}", academictestsubjectsId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), AcademicTestSubjects.class.getSimpleName(), academictestsubjectsId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
    public void delete(AcademicTestSubjects academicTestSubjects) {
        LOGGER.debug("Deleting AcademicTestSubjects with {}", academicTestSubjects);
        this.wmGenericDao.delete(academicTestSubjects);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<AcademicTestSubjects> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all AcademicTestSubjects");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<AcademicTestSubjects> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all AcademicTestSubjects");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service School_DB for table AcademicTestSubjects to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service School_DB for table AcademicTestSubjects to {} format", options.getExportType());
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
    public Page<TestConducted> findAssociatedTestConducteds(String academicYear, Integer subjectId, Integer testId, Integer standardId, Pageable pageable) {
        LOGGER.debug("Fetching all associated testConducteds");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("academicTestSubjects.academicYear = '" + academicYear + "'");
        queryBuilder.append(" and ");
        queryBuilder.append("academicTestSubjects.subjectId = '" + subjectId + "'");
        queryBuilder.append(" and ");
        queryBuilder.append("academicTestSubjects.testId = '" + testId + "'");
        queryBuilder.append(" and ");
        queryBuilder.append("academicTestSubjects.standardId = '" + standardId + "'");

        return testConductedService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service TestConductedService instance
     */
    protected void setTestConductedService(TestConductedService service) {
        this.testConductedService = service;
    }

}