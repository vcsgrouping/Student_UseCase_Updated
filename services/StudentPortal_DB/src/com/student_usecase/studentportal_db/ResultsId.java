/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentportal_db;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ResultsId implements Serializable {

    private Integer rollnumber;
    private Date academicYear;
    private String grade;
    private Short marks;
    private String standard;

    public Integer getRollnumber() {
        return this.rollnumber;
    }

    public void setRollnumber(Integer rollnumber) {
        this.rollnumber = rollnumber;
    }

    public Date getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(Date academicYear) {
        this.academicYear = academicYear;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Short getMarks() {
        return this.marks;
    }

    public void setMarks(Short marks) {
        this.marks = marks;
    }

    public String getStandard() {
        return this.standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Results)) return false;
        final Results results = (Results) o;
        return Objects.equals(getRollnumber(), results.getRollnumber()) &&
                Objects.equals(getAcademicYear(), results.getAcademicYear()) &&
                Objects.equals(getGrade(), results.getGrade()) &&
                Objects.equals(getMarks(), results.getMarks()) &&
                Objects.equals(getStandard(), results.getStandard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRollnumber(),
                getAcademicYear(),
                getGrade(),
                getMarks(),
                getStandard());
    }
}