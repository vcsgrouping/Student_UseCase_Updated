/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.export.ExportOptions;

import com.student_usecase.school_db.models.query.*;

public interface School_DBQueryExecutorService {

    Page<SvTop3studentsFromAllStandardsResponse> executeSV_Top3StudentsFromAllStandards(Integer testid, String academicyear, Pageable pageable);

    void exportSV_Top3StudentsFromAllStandards(Integer testid, String academicyear, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<SvAcademicSubjectsByStandardResponse> executeSV_AcademicSubjectsByStandard(String year, Integer standard, Pageable pageable);

    void exportSV_AcademicSubjectsByStandard(String year, Integer standard, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<SvCountOfStudentPerGradeResponse> executeSV_CountOfStudentPerGrade(String academicYear, Integer standardid, Pageable pageable);

    void exportSV_CountOfStudentPerGrade(String academicYear, Integer standardid, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<SvCountOfStudentsResponse> executeSV_CountOfStudents(Integer standard, String year, Pageable pageable);

    void exportSV_CountOfStudents(Integer standard, String year, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<SvGetStudentResultsForStandardResponse> executeSV_GetSTudentResultsForStandard(String academicYear, Integer standardid, Integer testid, Pageable pageable);

    void exportSV_GetSTudentResultsForStandard(String academicYear, Integer standardid, Integer testid, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

}