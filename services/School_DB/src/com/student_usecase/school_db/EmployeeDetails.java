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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * EmployeeDetails generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`EMPLOYEE_DETAILS`")
public class EmployeeDetails implements Serializable {

    private Integer empId;
    private String firstname;
    private String lastname;
    private String state;
    private String city;
    private String street;
    private String zip;
    private Date dateOfBirth;
    private String jobTitle;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] picUrl;
    private Integer managerId;
    private EmployeeDetails employeeDetailsByManagerId;
    private List<EmployeeDetails> employeeDetailsesForManagerId;
    private UserLogin userLogin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`EMP_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getEmpId() {
        return this.empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @Column(name = "`FIRSTNAME`", nullable = true, length = 255)
    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "`LASTNAME`", nullable = true, length = 255)
    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "`STATE`", nullable = true, length = 255)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "`CITY`", nullable = true, length = 255)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "`STREET`", nullable = true, length = 255)
    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "`ZIP`", nullable = true, length = 255)
    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "`DATE_OF_BIRTH`", nullable = true)
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "`JOB_TITLE`", nullable = true, length = 255)
    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Column(name = "`PIC_URL`", nullable = true)
    public byte[] getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(byte[] picUrl) {
        this.picUrl = picUrl;
    }

    @Column(name = "`MANAGER_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getManagerId() {
        return this.managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    // ignoring self relation properties to avoid circular loops & unwanted fields from the related entity.
    @JsonIgnoreProperties({"employeeDetailsByManagerId", "employeeDetailsesForManagerId"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`MANAGER_ID`", referencedColumnName = "`EMP_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_EMPLOYEE_DETAILS_TO_EMFB1X`"))
    @Fetch(FetchMode.JOIN)
    public EmployeeDetails getEmployeeDetailsByManagerId() {
        return this.employeeDetailsByManagerId;
    }

    public void setEmployeeDetailsByManagerId(EmployeeDetails employeeDetailsByManagerId) {
        if(employeeDetailsByManagerId != null) {
            this.managerId = employeeDetailsByManagerId.getEmpId();
        }

        this.employeeDetailsByManagerId = employeeDetailsByManagerId;
    }
    // ignoring self relation properties to avoid circular loops & unwanted fields from the related entity.
    @JsonIgnoreProperties({"employeeDetailsByManagerId", "employeeDetailsesForManagerId"})
    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeDetailsByManagerId")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    public List<EmployeeDetails> getEmployeeDetailsesForManagerId() {
        return this.employeeDetailsesForManagerId;
    }

    public void setEmployeeDetailsesForManagerId(List<EmployeeDetails> employeeDetailsesForManagerId) {
        this.employeeDetailsesForManagerId = employeeDetailsesForManagerId;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employeeDetails")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    public UserLogin getUserLogin() {
        return this.userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @PostPersist
    public void onPostPersist() {
        if(employeeDetailsesForManagerId != null) {
            employeeDetailsesForManagerId.forEach(_employeeDetails -> _employeeDetails.setEmployeeDetailsByManagerId(this));
        }
        if(userLogin != null) {
            userLogin.setEmployeeDetails(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDetails)) return false;
        final EmployeeDetails employeeDetails = (EmployeeDetails) o;
        return Objects.equals(getEmpId(), employeeDetails.getEmpId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmpId());
    }
}