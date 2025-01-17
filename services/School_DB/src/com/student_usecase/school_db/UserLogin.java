/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * UserLogin generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`USER_LOGIN`")
public class UserLogin implements Serializable {

    private Integer userId;
    private String userName;
    private String password;
    private String role;
    private EmployeeDetails employeeDetails;

    @Id
    @Column(name = "`USER_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "`USER_NAME`", nullable = true, length = 255)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "`PASSWORD`", nullable = true, length = 255)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "`ROLE`", nullable = true, length = 255)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`USER_ID`", referencedColumnName = "`EMP_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_USER_LOGIN_TO_EMPLOYEvxmPp`"))
    @Fetch(FetchMode.JOIN)
    public EmployeeDetails getEmployeeDetails() {
        return this.employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        if(employeeDetails != null) {
            this.userId = employeeDetails.getEmpId();
        }

        this.employeeDetails = employeeDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLogin)) return false;
        final UserLogin userLogin = (UserLogin) o;
        return Objects.equals(getUserId(), userLogin.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}