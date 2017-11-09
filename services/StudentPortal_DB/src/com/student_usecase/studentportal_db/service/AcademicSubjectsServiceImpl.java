/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentportal_db.service;

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
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.student_usecase.studentportal_db.AcademicSubjects;
import com.student_usecase.studentportal_db.AcademicSubjectsId;


/**
 * ServiceImpl object for domain model class AcademicSubjects.
 *
 * @see AcademicSubjects
 */
@Service("StudentPortal_DB.AcademicSubjectsService")
@Validated
public class AcademicSubjectsServiceImpl implements AcademicSubjectsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcademicSubjectsServiceImpl.class);


    @Autowired
    @Qualifier("StudentPortal_DB.AcademicSubjectsDao")
    private WMGenericDao<AcademicSubjects, AcademicSubjectsId> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<AcademicSubjects, AcademicSubjectsId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "StudentPortal_DBTransactionManager")
    @Override
	public AcademicSubjects create(AcademicSubjects academicSubjects) {
        LOGGER.debug("Creating a new AcademicSubjects with information: {}", academicSubjects);
        AcademicSubjects academicSubjectsCreated = this.wmGenericDao.create(academicSubjects);
        return academicSubjectsCreated;
    }

	@Transactional(readOnly = true, value = "StudentPortal_DBTransactionManager")
	@Override
	public AcademicSubjects getById(AcademicSubjectsId academicsubjectsId) throws EntityNotFoundException {
        LOGGER.debug("Finding AcademicSubjects by id: {}", academicsubjectsId);
        AcademicSubjects academicSubjects = this.wmGenericDao.findById(academicsubjectsId);
        if (academicSubjects == null){
            LOGGER.debug("No AcademicSubjects found with id: {}", academicsubjectsId);
            throw new EntityNotFoundException(String.valueOf(academicsubjectsId));
        }
        return academicSubjects;
    }

    @Transactional(readOnly = true, value = "StudentPortal_DBTransactionManager")
	@Override
	public AcademicSubjects findById(AcademicSubjectsId academicsubjectsId) {
        LOGGER.debug("Finding AcademicSubjects by id: {}", academicsubjectsId);
        return this.wmGenericDao.findById(academicsubjectsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "StudentPortal_DBTransactionManager")
	@Override
	public AcademicSubjects update(AcademicSubjects academicSubjects) throws EntityNotFoundException {
        LOGGER.debug("Updating AcademicSubjects with information: {}", academicSubjects);
        this.wmGenericDao.update(academicSubjects);

        AcademicSubjectsId academicsubjectsId = new AcademicSubjectsId();
        academicsubjectsId.setAcademicYear(academicSubjects.getAcademicYear());
        academicsubjectsId.setStandard(academicSubjects.getStandard());
        academicsubjectsId.setSubjectName(academicSubjects.getSubjectName());
        academicsubjectsId.setSubjectTeacher(academicSubjects.getSubjectTeacher());

        return this.wmGenericDao.findById(academicsubjectsId);
    }

    @Transactional(value = "StudentPortal_DBTransactionManager")
	@Override
	public AcademicSubjects delete(AcademicSubjectsId academicsubjectsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting AcademicSubjects with id: {}", academicsubjectsId);
        AcademicSubjects deleted = this.wmGenericDao.findById(academicsubjectsId);
        if (deleted == null) {
            LOGGER.debug("No AcademicSubjects found with id: {}", academicsubjectsId);
            throw new EntityNotFoundException(String.valueOf(academicsubjectsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "StudentPortal_DBTransactionManager")
	@Override
	public Page<AcademicSubjects> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all AcademicSubjects");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "StudentPortal_DBTransactionManager")
    @Override
    public Page<AcademicSubjects> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all AcademicSubjects");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "StudentPortal_DBTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service StudentPortal_DB for table AcademicSubjects to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "StudentPortal_DBTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "StudentPortal_DBTransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}

