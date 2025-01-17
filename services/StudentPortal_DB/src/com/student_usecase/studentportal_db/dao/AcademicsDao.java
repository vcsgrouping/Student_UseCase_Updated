/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentportal_db.dao;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wavemaker.runtime.data.dao.WMGenericDaoImpl;
import com.wavemaker.runtime.data.dao.query.types.wmql.WMQLTypeHelper;

import com.student_usecase.studentportal_db.Academics;
import com.student_usecase.studentportal_db.AcademicsId;

/**
 * Specifies methods used to obtain and modify Academics related information
 * which is stored in the database.
 */
@Repository("StudentPortal_DB.AcademicsDao")
public class AcademicsDao extends WMGenericDaoImpl<Academics, AcademicsId> {

    @Autowired
    @Qualifier("StudentPortal_DBTemplate")
    private HibernateTemplate template;

    @Autowired
    @Qualifier("StudentPortal_DBWMQLTypeHelper")
    private WMQLTypeHelper wmqlTypeHelper;


    @Override
    public HibernateTemplate getTemplate() {
        return this.template;
    }

    @Override
    public WMQLTypeHelper getWMQLTypeHelper() {
        return this.wmqlTypeHelper;
    }

}