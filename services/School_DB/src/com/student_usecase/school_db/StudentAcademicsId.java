/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

public class StudentAcademicsId implements Serializable {

    private String academicYear;
    private Integer standardId;
    private Integer studentId;

    public String getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getStandardId() {
        return this.standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentAcademics)) return false;
        final StudentAcademics studentAcademics = (StudentAcademics) o;
        return Objects.equals(getAcademicYear(), studentAcademics.getAcademicYear()) &&
                Objects.equals(getStandardId(), studentAcademics.getStandardId()) &&
                Objects.equals(getStudentId(), studentAcademics.getStudentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAcademicYear(),
                getStandardId(),
                getStudentId());
    }
}