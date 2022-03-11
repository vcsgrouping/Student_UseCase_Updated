/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.runtime.data.model.QueryProcedureInput;

import com.student_usecase.school_db.models.query.*;

@Service
public class School_DBQueryExecutorServiceImpl implements School_DBQueryExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(School_DBQueryExecutorServiceImpl.class);

    @Autowired
    @Qualifier("School_DBWMQueryExecutor")
    private WMQueryExecutor queryExecutor;

    @Transactional(value = "School_DBTransactionManager", readOnly = true)
    @Override
    public Page<SvTop3studentsFromAllStandardsResponse> executeSV_Top3StudentsFromAllStandards(Integer testid, String academicyear, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(2);

        params.put("TESTID", testid);
        params.put("ACADEMICYEAR", academicyear);

        return queryExecutor.executeNamedQuery("SV_Top3StudentsFromAllStandards", params, SvTop3studentsFromAllStandardsResponse.class, pageable);
    }

    @Transactional(value = "School_DBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportSV_Top3StudentsFromAllStandards(Integer testid, String academicyear, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(2);

        params.put("TESTID", testid);
        params.put("ACADEMICYEAR", academicyear);

        QueryProcedureInput<SvTop3studentsFromAllStandardsResponse> queryInput = new QueryProcedureInput<>("SV_Top3StudentsFromAllStandards", params, SvTop3studentsFromAllStandardsResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "School_DBTransactionManager", readOnly = true)
    @Override
    public Page<SvAcademicSubjectsByStandardResponse> executeSV_AcademicSubjectsByStandard(String year, Integer standard, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(2);

        params.put("year", year);
        params.put("standard", standard);

        return queryExecutor.executeNamedQuery("SV_AcademicSubjectsByStandard", params, SvAcademicSubjectsByStandardResponse.class, pageable);
    }

    @Transactional(value = "School_DBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportSV_AcademicSubjectsByStandard(String year, Integer standard, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(2);

        params.put("year", year);
        params.put("standard", standard);

        QueryProcedureInput<SvAcademicSubjectsByStandardResponse> queryInput = new QueryProcedureInput<>("SV_AcademicSubjectsByStandard", params, SvAcademicSubjectsByStandardResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "School_DBTransactionManager", readOnly = true)
    @Override
    public Page<SvCountOfStudentPerGradeResponse> executeSV_CountOfStudentPerGrade(String academicYear, Integer standardid, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(2);

        params.put("academicYear", academicYear);
        params.put("standardid", standardid);

        return queryExecutor.executeNamedQuery("SV_CountOfStudentPerGrade", params, SvCountOfStudentPerGradeResponse.class, pageable);
    }

    @Transactional(value = "School_DBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportSV_CountOfStudentPerGrade(String academicYear, Integer standardid, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(2);

        params.put("academicYear", academicYear);
        params.put("standardid", standardid);

        QueryProcedureInput<SvCountOfStudentPerGradeResponse> queryInput = new QueryProcedureInput<>("SV_CountOfStudentPerGrade", params, SvCountOfStudentPerGradeResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "School_DBTransactionManager", readOnly = true)
    @Override
    public Page<SvCountOfStudentsResponse> executeSV_CountOfStudents(Integer standard, String year, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(2);

        params.put("standard", standard);
        params.put("year", year);

        return queryExecutor.executeNamedQuery("SV_CountOfStudents", params, SvCountOfStudentsResponse.class, pageable);
    }

    @Transactional(value = "School_DBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportSV_CountOfStudents(Integer standard, String year, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(2);

        params.put("standard", standard);
        params.put("year", year);

        QueryProcedureInput<SvCountOfStudentsResponse> queryInput = new QueryProcedureInput<>("SV_CountOfStudents", params, SvCountOfStudentsResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "School_DBTransactionManager", readOnly = true)
    @Override
    public Page<SvGetStudentResultsForStandardResponse> executeSV_GetSTudentResultsForStandard(String academicYear, Integer standardid, Integer testid, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(3);

        params.put("academicYear", academicYear);
        params.put("standardid", standardid);
        params.put("testid", testid);

        return queryExecutor.executeNamedQuery("SV_GetSTudentResultsForStandard", params, SvGetStudentResultsForStandardResponse.class, pageable);
    }

    @Transactional(value = "School_DBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportSV_GetSTudentResultsForStandard(String academicYear, Integer standardid, Integer testid, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(3);

        params.put("academicYear", academicYear);
        params.put("standardid", standardid);
        params.put("testid", testid);

        QueryProcedureInput<SvGetStudentResultsForStandardResponse> queryInput = new QueryProcedureInput<>("SV_GetSTudentResultsForStandard", params, SvGetStudentResultsForStandardResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

}