/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
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
 * AcademicTestSubjects generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ACADEMIC_TEST_SUBJECTS`")
@IdClass(AcademicTestSubjectsId.class)
public class AcademicTestSubjects implements Serializable {

    private String academicYear;
    private Integer subjectId;
    private Integer testId;
    private Integer standardId;
    private String maxMarks;
    private AcademicSubjects academicSubjects;
    private TestDetails testDetails;
    private List<TestConducted> testConducteds;

    @Id
    @Column(name = "`ACADEMIC_YEAR`", nullable = false, length = 255)
    public String getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    @Id
    @Column(name = "`SUBJECT_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Id
    @Column(name = "`TEST_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getTestId() {
        return this.testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Id
    @Column(name = "`STANDARD_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getStandardId() {
        return this.standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    @Column(name = "`MAX_MARKS`", nullable = true, length = 255)
    public String getMaxMarks() {
        return this.maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns(value = {
            @JoinColumn(name = "`ACADEMIC_YEAR`", referencedColumnName = "`ACADEMIC_YEAR`", insertable = false, updatable = false),
            @JoinColumn(name = "`STANDARD_ID`", referencedColumnName = "`STANDARD_ID`", insertable = false, updatable = false),
            @JoinColumn(name = "`SUBJECT_ID`", referencedColumnName = "`SUBJECT_ID`", insertable = false, updatable = false)},
        foreignKey = @ForeignKey(name = "`FK_ACADEMIC_TEST_SUBJECTGRO6a`"))
    @Fetch(FetchMode.JOIN)
    public AcademicSubjects getAcademicSubjects() {
        return this.academicSubjects;
    }

    public void setAcademicSubjects(AcademicSubjects academicSubjects) {
        if(academicSubjects != null) {
            this.academicYear = academicSubjects.getAcademicYear();
            this.standardId = academicSubjects.getStandardId();
            this.subjectId = academicSubjects.getSubjectId();
        }

        this.academicSubjects = academicSubjects;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`TEST_ID`", referencedColumnName = "`TEST_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_ACADEMIC_TEST_SUBJECTkgNBV`"))
    @Fetch(FetchMode.JOIN)
    public TestDetails getTestDetails() {
        return this.testDetails;
    }

    public void setTestDetails(TestDetails testDetails) {
        if(testDetails != null) {
            this.testId = testDetails.getTestId();
        }

        this.testDetails = testDetails;
    }
    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "academicTestSubjects")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    public List<TestConducted> getTestConducteds() {
        return this.testConducteds;
    }

    public void setTestConducteds(List<TestConducted> testConducteds) {
        this.testConducteds = testConducteds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcademicTestSubjects)) return false;
        final AcademicTestSubjects academicTestSubjects = (AcademicTestSubjects) o;
        return Objects.equals(getAcademicYear(), academicTestSubjects.getAcademicYear()) &&
                Objects.equals(getSubjectId(), academicTestSubjects.getSubjectId()) &&
                Objects.equals(getTestId(), academicTestSubjects.getTestId()) &&
                Objects.equals(getStandardId(), academicTestSubjects.getStandardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAcademicYear(),
                getSubjectId(),
                getTestId(),
                getStandardId());
    }
}