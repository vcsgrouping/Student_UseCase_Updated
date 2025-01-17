/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentportal_db.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.MapTo;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.student_usecase.studentportal_db.UserLogin;
import com.student_usecase.studentportal_db.service.UserLoginService;


/**
 * Controller object for domain model class UserLogin.
 * @see UserLogin
 */
@RestController("StudentPortal_DB.UserLoginController")
@Api(value = "UserLoginController", description = "Exposes APIs to work with UserLogin resource.")
@RequestMapping("/StudentPortal_DB/UserLogin")
public class UserLoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
	@Qualifier("StudentPortal_DB.UserLoginService")
	private UserLoginService userLoginService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new UserLogin instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserLogin createUserLogin(@RequestBody UserLogin userLogin) {
		LOGGER.debug("Create UserLogin with information: {}" , userLogin);

		userLogin = userLoginService.create(userLogin);
		LOGGER.debug("Created UserLogin with information: {}" , userLogin);

	    return userLogin;
	}

    @ApiOperation(value = "Returns the UserLogin instance associated with the given id.")
    @RequestMapping(value = "/{userId:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserLogin getUserLogin(@PathVariable("userId") Integer userId) {
        LOGGER.debug("Getting UserLogin with id: {}" , userId);

        UserLogin foundUserLogin = userLoginService.getById(userId);
        LOGGER.debug("UserLogin details with id: {}" , foundUserLogin);

        return foundUserLogin;
    }

    @ApiOperation(value = "Updates the UserLogin instance associated with the given id.")
    @RequestMapping(value = "/{userId:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserLogin editUserLogin(@PathVariable("userId") Integer userId, @RequestBody UserLogin userLogin) {
        LOGGER.debug("Editing UserLogin with id: {}" , userLogin.getUserId());

        userLogin.setUserId(userId);
        userLogin = userLoginService.update(userLogin);
        LOGGER.debug("UserLogin details with id: {}" , userLogin);

        return userLogin;
    }
    
    @ApiOperation(value = "Partially updates the UserLogin instance associated with the given id.")
    @RequestMapping(value = "/{userId:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserLogin patchUserLogin(@PathVariable("userId") Integer userId, @RequestBody @MapTo(UserLogin.class) Map<String, Object> userLoginPatch) {
        LOGGER.debug("Partially updating UserLogin with id: {}" , userId);

        UserLogin userLogin = userLoginService.partialUpdate(userId, userLoginPatch);
        LOGGER.debug("UserLogin details after partial update: {}" , userLogin);

        return userLogin;
    }

    @ApiOperation(value = "Deletes the UserLogin instance associated with the given id.")
    @RequestMapping(value = "/{userId:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteUserLogin(@PathVariable("userId") Integer userId) {
        LOGGER.debug("Deleting UserLogin with id: {}" , userId);

        UserLogin deletedUserLogin = userLoginService.delete(userId);

        return deletedUserLogin != null;
    }

    /**
     * @deprecated Use {@link #findUserLogins(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of UserLogin instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserLogin> searchUserLoginsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering UserLogins list by query filter:{}", (Object) queryFilters);
        return userLoginService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of UserLogin instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserLogin> findUserLogins(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering UserLogins list by filter:", query);
        return userLoginService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of UserLogin instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserLogin> filterUserLogins(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering UserLogins list by filter", query);
        return userLoginService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportUserLogins(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return userLoginService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportUserLoginsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = UserLogin.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> userLoginService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of UserLogin instances matching the optional query (q) request param.")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countUserLogins( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting UserLogins");
		return userLoginService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getUserLoginAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return userLoginService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserLoginService instance
	 */
	protected void setUserLoginService(UserLoginService service) {
		this.userLoginService = service;
	}

}