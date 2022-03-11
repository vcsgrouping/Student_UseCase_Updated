/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class SvGetStudentResultsForStandardResponse implements Serializable {


    @JsonProperty("MARKS_SECURED")
    @ColumnAlias("MARKS_SECURED")
    private BigDecimal marksSecured;

    @JsonProperty("STUDENTID")
    @ColumnAlias("STUDENTID")
    private Integer studentid;

    @JsonProperty("STUDENTNAME")
    @ColumnAlias("STUDENTNAME")
    private String studentname;

    @JsonProperty("TESTID")
    @ColumnAlias("TESTID")
    private Integer testid;

    @JsonProperty("TESTNAME")
    @ColumnAlias("TESTNAME")
    private String testname;

    @JsonProperty("TEST_CONDUCTED_ID")
    @ColumnAlias("TEST_CONDUCTED_ID")
    private Integer testConductedId;

    public BigDecimal getMarksSecured() {
        return this.marksSecured;
    }

    public void setMarksSecured(BigDecimal marksSecured) {
        this.marksSecured = marksSecured;
    }

    public Integer getStudentid() {
        return this.studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return this.studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public Integer getTestid() {
        return this.testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    public String getTestname() {
        return this.testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public Integer getTestConductedId() {
        return this.testConductedId;
    }

    public void setTestConductedId(Integer testConductedId) {
        this.testConductedId = testConductedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SvGetStudentResultsForStandardResponse)) return false;
        final SvGetStudentResultsForStandardResponse svGetStudentResultsForStandardResponse = (SvGetStudentResultsForStandardResponse) o;
        return Objects.equals(getMarksSecured(), svGetStudentResultsForStandardResponse.getMarksSecured()) &&
                Objects.equals(getStudentid(), svGetStudentResultsForStandardResponse.getStudentid()) &&
                Objects.equals(getStudentname(), svGetStudentResultsForStandardResponse.getStudentname()) &&
                Objects.equals(getTestid(), svGetStudentResultsForStandardResponse.getTestid()) &&
                Objects.equals(getTestname(), svGetStudentResultsForStandardResponse.getTestname()) &&
                Objects.equals(getTestConductedId(), svGetStudentResultsForStandardResponse.getTestConductedId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarksSecured(),
                getStudentid(),
                getStudentname(),
                getTestid(),
                getTestname(),
                getTestConductedId());
    }
}