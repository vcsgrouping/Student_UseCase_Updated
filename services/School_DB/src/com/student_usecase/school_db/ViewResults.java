/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * ViewResults generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`VIEW_RESULTS`")
@IdClass(ViewResultsId.class)
public class ViewResults implements Serializable {

    private BigInteger totalMarks;
    private String academicYear;
    private Integer standardId;
    private Integer studentId;
    private String studentName;
    private Integer testId;

    @Id
    @Column(name = "`TOTAL_MARKS`", nullable = true, scale = 0, precision = 27)
    public BigInteger getTotalMarks() {
        return this.totalMarks;
    }

    public void setTotalMarks(BigInteger totalMarks) {
        this.totalMarks = totalMarks;
    }

    @Id
    @Column(name = "`ACADEMIC_YEAR`", nullable = false, length = 255)
    public String getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    @Id
    @Column(name = "`STANDARD_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getStandardId() {
        return this.standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    @Id
    @Column(name = "`STUDENT_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "`STUDENT_NAME`", nullable = false, length = 255)
    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Id
    @Column(name = "`TEST_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getTestId() {
        return this.testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewResults)) return false;
        final ViewResults viewResults = (ViewResults) o;
        return Objects.equals(getTotalMarks(), viewResults.getTotalMarks()) &&
                Objects.equals(getAcademicYear(), viewResults.getAcademicYear()) &&
                Objects.equals(getStandardId(), viewResults.getStandardId()) &&
                Objects.equals(getStudentId(), viewResults.getStudentId()) &&
                Objects.equals(getStudentName(), viewResults.getStudentName()) &&
                Objects.equals(getTestId(), viewResults.getTestId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotalMarks(),
                getAcademicYear(),
                getStandardId(),
                getStudentId(),
                getStudentName(),
                getTestId());
    }
}