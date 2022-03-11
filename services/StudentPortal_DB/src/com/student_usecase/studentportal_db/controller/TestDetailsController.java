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

import com.student_usecase.studentportal_db.TestDetails;
import com.student_usecase.studentportal_db.TestDetailsId;
import com.student_usecase.studentportal_db.service.TestDetailsService;


/**
 * Controller object for domain model class TestDetails.
 * @see TestDetails
 */
@RestController("StudentPortal_DB.TestDetailsController")
@Api(value = "TestDetailsController", description = "Exposes APIs to work with TestDetails resource.")
@RequestMapping("/StudentPortal_DB/TestDetails")
public class TestDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDetailsController.class);

    @Autowired
	@Qualifier("StudentPortal_DB.TestDetailsService")
	private TestDetailsService testDetailsService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new TestDetails instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TestDetails createTestDetails(@RequestBody TestDetails testDetails) {
		LOGGER.debug("Create TestDetails with information: {}" , testDetails);

		testDetails = testDetailsService.create(testDetails);
		LOGGER.debug("Created TestDetails with information: {}" , testDetails);

	    return testDetails;
	}

    @ApiOperation(value = "Returns the TestDetails instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TestDetails getTestDetails(@RequestParam("testName") String testName, @RequestParam("testId") Integer testId) {

        TestDetailsId testdetailsId = new TestDetailsId();
        testdetailsId.setTestName(testName);
        testdetailsId.setTestId(testId);

        LOGGER.debug("Getting TestDetails with id: {}" , testdetailsId);
        TestDetails testDetails = testDetailsService.getById(testdetailsId);
        LOGGER.debug("TestDetails details with id: {}" , testDetails);

        return testDetails;
    }



    @ApiOperation(value = "Updates the TestDetails instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TestDetails editTestDetails(@RequestParam("testName") String testName, @RequestParam("testId") Integer testId, @RequestBody TestDetails testDetails) {

        testDetails.setTestName(testName);
        testDetails.setTestId(testId);

        LOGGER.debug("TestDetails details with id is updated with: {}" , testDetails);

        return testDetailsService.update(testDetails);
    }

	@ApiOperation(value = "Partially updates the  TestDetails instance associated with the given composite-id.")
	@RequestMapping(value = "/composite-id", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TestDetails patchTestDetails(@RequestParam("testName") String testName, @RequestParam("testId") Integer testId, @RequestBody @MapTo(TestDetails.class) Map<String, Object> testDetailsPatch) {

        TestDetailsId testdetailsId = new TestDetailsId();
        testdetailsId.setTestName(testName);
        testdetailsId.setTestId(testId);
        LOGGER.debug("Partially updating TestDetails with id: {}" , testdetailsId);

        TestDetails testDetails = testDetailsService.partialUpdate(testdetailsId, testDetailsPatch);
        LOGGER.debug("TestDetails details after partial update: {}" , testDetails);

        return testDetails;
    }


    @ApiOperation(value = "Deletes the TestDetails instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteTestDetails(@RequestParam("testName") String testName, @RequestParam("testId") Integer testId) {

        TestDetailsId testdetailsId = new TestDetailsId();
        testdetailsId.setTestName(testName);
        testdetailsId.setTestId(testId);

        LOGGER.debug("Deleting TestDetails with id: {}" , testdetailsId);
        TestDetails testDetails = testDetailsService.delete(testdetailsId);

        return testDetails != null;
    }


    /**
     * @deprecated Use {@link #findTestDetails(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of TestDetails instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TestDetails> searchTestDetailsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering TestDetails list by query filter:{}", (Object) queryFilters);
        return testDetailsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of TestDetails instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TestDetails> findTestDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering TestDetails list by filter:", query);
        return testDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of TestDetails instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TestDetails> filterTestDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering TestDetails list by filter", query);
        return testDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportTestDetails(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return testDetailsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportTestDetailsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = TestDetails.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> testDetailsService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of TestDetails instances matching the optional query (q) request param.")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countTestDetails( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting TestDetails");
		return testDetailsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getTestDetailsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return testDetailsService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service TestDetailsService instance
	 */
	protected void setTestDetailsService(TestDetailsService service) {
		this.testDetailsService = service;
	}

}