/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentportal_db.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.student_usecase.studentportal_db.Results;
import com.student_usecase.studentportal_db.ResultsId;

/**
 * Service object for domain model class {@link Results}.
 */
public interface ResultsService {

    /**
     * Creates a new Results. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Results if any.
     *
     * @param results Details of the Results to be created; value cannot be null.
     * @return The newly created Results.
     */
    Results create(@Valid Results results);


	/**
     * Returns Results by given id if exists.
     *
     * @param resultsId The id of the Results to get; value cannot be null.
     * @return Results associated with the given resultsId.
	 * @throws EntityNotFoundException If no Results is found.
     */
    Results getById(ResultsId resultsId);

    /**
     * Find and return the Results by given id if exists, returns null otherwise.
     *
     * @param resultsId The id of the Results to get; value cannot be null.
     * @return Results associated with the given resultsId.
     */
    Results findById(ResultsId resultsId);

	/**
     * Find and return the list of Results by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param resultsIds The id's of the Results to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Results associated with the given resultsIds.
     */
    List<Results> findByMultipleIds(List<ResultsId> resultsIds, boolean orderedReturn);


    /**
     * Updates the details of an existing Results. It replaces all fields of the existing Results with the given results.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Results if any.
     *
     * @param results The details of the Results to be updated; value cannot be null.
     * @return The updated Results.
     * @throws EntityNotFoundException if no Results is found with given input.
     */
    Results update(@Valid Results results);


    /**
     * Partially updates the details of an existing Results. It updates only the
     * fields of the existing Results which are passed in the resultsPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Results if any.
     *
     * @param resultsId The id of the Results to be deleted; value cannot be null.
     * @param resultsPatch The partial data of Results which is supposed to be updated; value cannot be null.
     * @return The updated Results.
     * @throws EntityNotFoundException if no Results is found with given input.
     */
    Results partialUpdate(ResultsId resultsId, Map<String, Object> resultsPatch);

    /**
     * Deletes an existing Results with the given id.
     *
     * @param resultsId The id of the Results to be deleted; value cannot be null.
     * @return The deleted Results.
     * @throws EntityNotFoundException if no Results found with the given id.
     */
    Results delete(ResultsId resultsId);

    /**
     * Deletes an existing Results with the given object.
     *
     * @param results The instance of the Results to be deleted; value cannot be null.
     */
    void delete(Results results);

    /**
     * Find all Results matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Results.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Results> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Results matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Results.
     *
     * @see Pageable
     * @see Page
     */
    Page<Results> findAll(String query, Pageable pageable);

    /**
     * Exports all Results matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all Results matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the Results in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Results.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);


}