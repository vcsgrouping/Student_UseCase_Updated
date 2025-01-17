/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * TestConducted generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`TEST_CONDUCTED`")
@IdClass(TestConductedId.class)
public class TestConducted implements Serializable {

    private String academicYear;
    private Integer testConductedId;
    private Integer standardId;
    private int subjectId;
    private int testId;
    private Date testConductedOn;
    private AcademicTestSubjects academicTestSubjects;
    private List<Results> resultses;

    @Id
    @Column(name = "`ACADEMIC_YEAR`", nullable = false, length = 255)
    public String getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    @Id
    @Column(name = "`TEST_CONDUCTED_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getTestConductedId() {
        return this.testConductedId;
    }

    public void setTestConductedId(Integer testConductedId) {
        this.testConductedId = testConductedId;
    }

    @Id
    @Column(name = "`STANDARD_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getStandardId() {
        return this.standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    @Column(name = "`SUBJECT_ID`", nullable = false, scale = 0, precision = 10)
    public int getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name = "`TEST_ID`", nullable = false, scale = 0, precision = 10)
    public int getTestId() {
        return this.testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Column(name = "`TEST_CONDUCTED_ON`", nullable = true)
    public Date getTestConductedOn() {
        return this.testConductedOn;
    }

    public void setTestConductedOn(Date testConductedOn) {
        this.testConductedOn = testConductedOn;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns(value = {
            @JoinColumn(name = "`ACADEMIC_YEAR`", referencedColumnName = "`ACADEMIC_YEAR`", insertable = false, updatable = false),
            @JoinColumn(name = "`STANDARD_ID`", referencedColumnName = "`STANDARD_ID`", insertable = false, updatable = false),
            @JoinColumn(name = "`TEST_ID`", referencedColumnName = "`TEST_ID`", insertable = false, updatable = false),
            @JoinColumn(name = "`SUBJECT_ID`", referencedColumnName = "`SUBJECT_ID`", insertable = false, updatable = false)},
        foreignKey = @ForeignKey(name = "`FK_TEST_CONDUCTED_TO_ACAsSyPO`"))
    @Fetch(FetchMode.JOIN)
    public AcademicTestSubjects getAcademicTestSubjects() {
        return this.academicTestSubjects;
    }

    public void setAcademicTestSubjects(AcademicTestSubjects academicTestSubjects) {
        if(academicTestSubjects != null) {
            this.academicYear = academicTestSubjects.getAcademicYear();
            this.standardId = academicTestSubjects.getStandardId();
            this.testId = academicTestSubjects.getTestId();
            this.subjectId = academicTestSubjects.getSubjectId();
        }

        this.academicTestSubjects = academicTestSubjects;
    }
    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "testConducted")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    public List<Results> getResultses() {
        return this.resultses;
    }

    public void setResultses(List<Results> resultses) {
        this.resultses = resultses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestConducted)) return false;
        final TestConducted testConducted = (TestConducted) o;
        return Objects.equals(getAcademicYear(), testConducted.getAcademicYear()) &&
                Objects.equals(getTestConductedId(), testConducted.getTestConductedId()) &&
                Objects.equals(getStandardId(), testConducted.getStandardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAcademicYear(),
                getTestConductedId(),
                getStandardId());
    }
}