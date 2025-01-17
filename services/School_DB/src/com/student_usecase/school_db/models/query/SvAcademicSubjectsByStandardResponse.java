/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class SvAcademicSubjectsByStandardResponse implements Serializable {


    @JsonProperty("STANDARD_NAME")
    @ColumnAlias("STANDARD_NAME")
    private String standardName;

    @JsonProperty("SUBJECT_NAME")
    @ColumnAlias("SUBJECT_NAME")
    private String subjectName;

    @JsonProperty("SUBJECT_TEACHER")
    @ColumnAlias("SUBJECT_TEACHER")
    private String subjectTeacher;

    public String getStandardName() {
        return this.standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectTeacher() {
        return this.subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SvAcademicSubjectsByStandardResponse)) return false;
        final SvAcademicSubjectsByStandardResponse svAcademicSubjectsByStandardResponse = (SvAcademicSubjectsByStandardResponse) o;
        return Objects.equals(getStandardName(), svAcademicSubjectsByStandardResponse.getStandardName()) &&
                Objects.equals(getSubjectName(), svAcademicSubjectsByStandardResponse.getSubjectName()) &&
                Objects.equals(getSubjectTeacher(), svAcademicSubjectsByStandardResponse.getSubjectTeacher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStandardName(),
                getSubjectName(),
                getSubjectTeacher());
    }
}