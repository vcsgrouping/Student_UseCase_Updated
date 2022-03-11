/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.controller;

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

import com.student_usecase.school_db.Academics;
import com.student_usecase.school_db.StandardDetails;
import com.student_usecase.school_db.service.StandardDetailsService;


/**
 * Controller object for domain model class StandardDetails.
 * @see StandardDetails
 */
@RestController("School_DB.StandardDetailsController")
@Api(value = "StandardDetailsController", description = "Exposes APIs to work with StandardDetails resource.")
@RequestMapping("/School_DB/StandardDetails")
public class StandardDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandardDetailsController.class);

    @Autowired
	@Qualifier("School_DB.StandardDetailsService")
	private StandardDetailsService standardDetailsService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new StandardDetails instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StandardDetails createStandardDetails(@RequestBody StandardDetails standardDetails) {
		LOGGER.debug("Create StandardDetails with information: {}" , standardDetails);

		standardDetails = standardDetailsService.create(standardDetails);
		LOGGER.debug("Created StandardDetails with information: {}" , standardDetails);

	    return standardDetails;
	}

    @ApiOperation(value = "Returns the StandardDetails instance associated with the given id.")
    @RequestMapping(value = "/{standardId:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StandardDetails getStandardDetails(@PathVariable("standardId") Integer standardId) {
        LOGGER.debug("Getting StandardDetails with id: {}" , standardId);

        StandardDetails foundStandardDetails = standardDetailsService.getById(standardId);
        LOGGER.debug("StandardDetails details with id: {}" , foundStandardDetails);

        return foundStandardDetails;
    }

    @ApiOperation(value = "Updates the StandardDetails instance associated with the given id.")
    @RequestMapping(value = "/{standardId:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StandardDetails editStandardDetails(@PathVariable("standardId") Integer standardId, @RequestBody StandardDetails standardDetails) {
        LOGGER.debug("Editing StandardDetails with id: {}" , standardDetails.getStandardId());

        standardDetails.setStandardId(standardId);
        standardDetails = standardDetailsService.update(standardDetails);
        LOGGER.debug("StandardDetails details with id: {}" , standardDetails);

        return standardDetails;
    }
    
    @ApiOperation(value = "Partially updates the StandardDetails instance associated with the given id.")
    @RequestMapping(value = "/{standardId:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StandardDetails patchStandardDetails(@PathVariable("standardId") Integer standardId, @RequestBody @MapTo(StandardDetails.class) Map<String, Object> standardDetailsPatch) {
        LOGGER.debug("Partially updating StandardDetails with id: {}" , standardId);

        StandardDetails standardDetails = standardDetailsService.partialUpdate(standardId, standardDetailsPatch);
        LOGGER.debug("StandardDetails details after partial update: {}" , standardDetails);

        return standardDetails;
    }

    @ApiOperation(value = "Deletes the StandardDetails instance associated with the given id.")
    @RequestMapping(value = "/{standardId:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteStandardDetails(@PathVariable("standardId") Integer standardId) {
        LOGGER.debug("Deleting StandardDetails with id: {}" , standardId);

        StandardDetails deletedStandardDetails = standardDetailsService.delete(standardId);

        return deletedStandardDetails != null;
    }

    @RequestMapping(value = "/standardCode/{standardCode}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching StandardDetails with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StandardDetails getByStandardCode(@PathVariable("standardCode") String standardCode) {
        LOGGER.debug("Getting StandardDetails with uniques key StandardCode");
        return standardDetailsService.getByStandardCode(standardCode);
    }

    /**
     * @deprecated Use {@link #findStandardDetails(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of StandardDetails instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StandardDetails> searchStandardDetailsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering StandardDetails list by query filter:{}", (Object) queryFilters);
        return standardDetailsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of StandardDetails instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StandardDetails> findStandardDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering StandardDetails list by filter:", query);
        return standardDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of StandardDetails instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StandardDetails> filterStandardDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering StandardDetails list by filter", query);
        return standardDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportStandardDetails(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return standardDetailsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportStandardDetailsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = StandardDetails.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> standardDetailsService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of StandardDetails instances matching the optional query (q) request param.")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countStandardDetails( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting StandardDetails");
		return standardDetailsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getStandardDetailsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return standardDetailsService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{standardId:.+}/academicses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the academicses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Academics> findAssociatedAcademicses(@PathVariable("standardId") Integer standardId, Pageable pageable) {

        LOGGER.debug("Fetching all associated academicses");
        return standardDetailsService.findAssociatedAcademicses(standardId, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service StandardDetailsService instance
	 */
	protected void setStandardDetailsService(StandardDetailsService service) {
		this.standardDetailsService = service;
	}

}