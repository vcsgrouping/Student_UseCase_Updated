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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * SubjectDetails generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`SUBJECT_DETAILS`", uniqueConstraints = {
            @UniqueConstraint(name = "`UK_SUBJECT_DETAILS_SUBJEuF03L`", columnNames = {"`SUBJECT_NAME`"})})
public class SubjectDetails implements Serializable {

    private Integer subjectId;
    private String subjectName;
    private List<AcademicSubjects> academicSubjectses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`SUBJECT_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name = "`SUBJECT_NAME`", nullable = false, length = 255)
    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subjectDetails")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    public List<AcademicSubjects> getAcademicSubjectses() {
        return this.academicSubjectses;
    }

    public void setAcademicSubjectses(List<AcademicSubjects> academicSubjectses) {
        this.academicSubjectses = academicSubjectses;
    }

    @PostPersist
    public void onPostPersist() {
        if(academicSubjectses != null) {
            academicSubjectses.forEach(_academicSubjects -> _academicSubjects.setSubjectDetails(this));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectDetails)) return false;
        final SubjectDetails subjectDetails = (SubjectDetails) o;
        return Objects.equals(getSubjectId(), subjectDetails.getSubjectId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubjectId());
    }
}