/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentportal_db;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * StandardDetails generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`STANDARD_DETAILS`")
@IdClass(StandardDetailsId.class)
public class StandardDetails implements Serializable {

    private String standardName;
    private String standardId;

    @Id
    @Column(name = "`STANDARD_NAME`", nullable = false, length = 255)
    public String getStandardName() {
        return this.standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    @Id
    @Column(name = "`STANDARD_ID`", nullable = false, length = 255)
    public String getStandardId() {
        return this.standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StandardDetails)) return false;
        final StandardDetails standardDetails = (StandardDetails) o;
        return Objects.equals(getStandardName(), standardDetails.getStandardName()) &&
                Objects.equals(getStandardId(), standardDetails.getStandardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStandardName(),
                getStandardId());
    }
}