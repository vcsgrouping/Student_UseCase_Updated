/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.student_usecase.school_db.Results;
import com.student_usecase.school_db.StudentAcademics;
import com.student_usecase.school_db.StudentDetails;


/**
 * ServiceImpl object for domain model class StudentDetails.
 *
 * @see StudentDetails
 */
@Service("School_DB.StudentDetailsService")
public class StudentDetailsServiceImpl implements StudentDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDetailsServiceImpl.class);

    @Autowired
	@Qualifier("School_DB.StudentAcademicsService")
	private StudentAcademicsService studentAcademicsService;

    @Autowired
	@Qualifier("School_DB.ResultsService")
	private ResultsService resultsService;

    @Autowired
    @Qualifier("School_DB.StudentDetailsDao")
    private WMGenericDao<StudentDetails, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<StudentDetails, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
	public StudentDetails create(StudentDetails studentDetails) {
        LOGGER.debug("Creating a new StudentDetails with information: {}", studentDetails);
        StudentDetails studentDetailsCreated = this.wmGenericDao.create(studentDetails);
        if(studentDetailsCreated.getResultses() != null) {
            for(Results resultse : studentDetailsCreated.getResultses()) {
                resultse.setStudentDetails(studentDetailsCreated);
                LOGGER.debug("Creating a new child Results with information: {}", resultse);
                resultsService.create(resultse);
            }
        }

        if(studentDetailsCreated.getStudentAcademicses() != null) {
            for(StudentAcademics studentAcademicse : studentDetailsCreated.getStudentAcademicses()) {
                studentAcademicse.setStudentDetails(studentDetailsCreated);
                LOGGER.debug("Creating a new child StudentAcademics with information: {}", studentAcademicse);
                studentAcademicsService.create(studentAcademicse);
            }
        }
        return studentDetailsCreated;
    }

	@Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public StudentDetails getById(Integer studentdetailsId) throws EntityNotFoundException {
        LOGGER.debug("Finding StudentDetails by id: {}", studentdetailsId);
        StudentDetails studentDetails = this.wmGenericDao.findById(studentdetailsId);
        if (studentDetails == null){
            LOGGER.debug("No StudentDetails found with id: {}", studentdetailsId);
            throw new EntityNotFoundException(String.valueOf(studentdetailsId));
        }
        return studentDetails;
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public StudentDetails findById(Integer studentdetailsId) {
        LOGGER.debug("Finding StudentDetails by id: {}", studentdetailsId);
        return this.wmGenericDao.findById(studentdetailsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "School_DBTransactionManager")
	@Override
	public StudentDetails update(StudentDetails studentDetails) throws EntityNotFoundException {
        LOGGER.debug("Updating StudentDetails with information: {}", studentDetails);
        this.wmGenericDao.update(studentDetails);

        Integer studentdetailsId = studentDetails.getStudentId();

        return this.wmGenericDao.findById(studentdetailsId);
    }

    @Transactional(value = "School_DBTransactionManager")
	@Override
	public StudentDetails delete(Integer studentdetailsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting StudentDetails with id: {}", studentdetailsId);
        StudentDetails deleted = this.wmGenericDao.findById(studentdetailsId);
        if (deleted == null) {
            LOGGER.debug("No StudentDetails found with id: {}", studentdetailsId);
            throw new EntityNotFoundException(String.valueOf(studentdetailsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public Page<StudentDetails> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all StudentDetails");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<StudentDetails> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all StudentDetails");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service School_DB for table StudentDetails to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
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
    public Page<Results> findAssociatedResultses(Integer studentId, Pageable pageable) {
        LOGGER.debug("Fetching all associated resultses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("studentDetails.studentId = '" + studentId + "'");

        return resultsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<StudentAcademics> findAssociatedStudentAcademicses(Integer studentId, Pageable pageable) {
        LOGGER.debug("Fetching all associated studentAcademicses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("studentDetails.studentId = '" + studentId + "'");

        return studentAcademicsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service StudentAcademicsService instance
	 */
	protected void setStudentAcademicsService(StudentAcademicsService service) {
        this.studentAcademicsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ResultsService instance
	 */
	protected void setResultsService(ResultsService service) {
        this.resultsService = service;
    }

}
