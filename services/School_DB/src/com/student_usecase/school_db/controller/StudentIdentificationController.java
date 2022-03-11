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

import com.student_usecase.school_db.StudentIdentification;
import com.student_usecase.school_db.service.StudentIdentificationService;


/**
 * Controller object for domain model class StudentIdentification.
 * @see StudentIdentification
 */
@RestController("School_DB.StudentIdentificationController")
@Api(value = "StudentIdentificationController", description = "Exposes APIs to work with StudentIdentification resource.")
@RequestMapping("/School_DB/StudentIdentification")
public class StudentIdentificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentIdentificationController.class);

    @Autowired
	@Qualifier("School_DB.StudentIdentificationService")
	private StudentIdentificationService studentIdentificationService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new StudentIdentification instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StudentIdentification createStudentIdentification(@RequestBody StudentIdentification studentIdentification) {
		LOGGER.debug("Create StudentIdentification with information: {}" , studentIdentification);

		studentIdentification = studentIdentificationService.create(studentIdentification);
		LOGGER.debug("Created StudentIdentification with information: {}" , studentIdentification);

	    return studentIdentification;
	}

    @ApiOperation(value = "Returns the StudentIdentification instance associated with the given id.")
    @RequestMapping(value = "/{identificationId:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StudentIdentification getStudentIdentification(@PathVariable("identificationId") Integer identificationId) {
        LOGGER.debug("Getting StudentIdentification with id: {}" , identificationId);

        StudentIdentification foundStudentIdentification = studentIdentificationService.getById(identificationId);
        LOGGER.debug("StudentIdentification details with id: {}" , foundStudentIdentification);

        return foundStudentIdentification;
    }

    @ApiOperation(value = "Updates the StudentIdentification instance associated with the given id.")
    @RequestMapping(value = "/{identificationId:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StudentIdentification editStudentIdentification(@PathVariable("identificationId") Integer identificationId, @RequestBody StudentIdentification studentIdentification) {
        LOGGER.debug("Editing StudentIdentification with id: {}" , studentIdentification.getIdentificationId());

        studentIdentification.setIdentificationId(identificationId);
        studentIdentification = studentIdentificationService.update(studentIdentification);
        LOGGER.debug("StudentIdentification details with id: {}" , studentIdentification);

        return studentIdentification;
    }
    
    @ApiOperation(value = "Partially updates the StudentIdentification instance associated with the given id.")
    @RequestMapping(value = "/{identificationId:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StudentIdentification patchStudentIdentification(@PathVariable("identificationId") Integer identificationId, @RequestBody @MapTo(StudentIdentification.class) Map<String, Object> studentIdentificationPatch) {
        LOGGER.debug("Partially updating StudentIdentification with id: {}" , identificationId);

        StudentIdentification studentIdentification = studentIdentificationService.partialUpdate(identificationId, studentIdentificationPatch);
        LOGGER.debug("StudentIdentification details after partial update: {}" , studentIdentification);

        return studentIdentification;
    }

    @ApiOperation(value = "Deletes the StudentIdentification instance associated with the given id.")
    @RequestMapping(value = "/{identificationId:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteStudentIdentification(@PathVariable("identificationId") Integer identificationId) {
        LOGGER.debug("Deleting StudentIdentification with id: {}" , identificationId);

        StudentIdentification deletedStudentIdentification = studentIdentificationService.delete(identificationId);

        return deletedStudentIdentification != null;
    }

    @RequestMapping(value = "/identificationNumber/{identificationNumber}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching StudentIdentification with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StudentIdentification getByIdentificationNumber(@PathVariable("identificationNumber") String identificationNumber) {
        LOGGER.debug("Getting StudentIdentification with uniques key IdentificationNumber");
        return studentIdentificationService.getByIdentificationNumber(identificationNumber);
    }

    /**
     * @deprecated Use {@link #findStudentIdentifications(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of StudentIdentification instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StudentIdentification> searchStudentIdentificationsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering StudentIdentifications list by query filter:{}", (Object) queryFilters);
        return studentIdentificationService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of StudentIdentification instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StudentIdentification> findStudentIdentifications(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering StudentIdentifications list by filter:", query);
        return studentIdentificationService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of StudentIdentification instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StudentIdentification> filterStudentIdentifications(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering StudentIdentifications list by filter", query);
        return studentIdentificationService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportStudentIdentifications(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return studentIdentificationService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportStudentIdentificationsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = StudentIdentification.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> studentIdentificationService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of StudentIdentification instances matching the optional query (q) request param.")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countStudentIdentifications( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting StudentIdentifications");
		return studentIdentificationService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getStudentIdentificationAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return studentIdentificationService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service StudentIdentificationService instance
	 */
	protected void setStudentIdentificationService(StudentIdentificationService service) {
		this.studentIdentificationService = service;
	}

}