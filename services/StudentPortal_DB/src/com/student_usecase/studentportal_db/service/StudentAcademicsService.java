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

import com.student_usecase.studentportal_db.StudentAcademics;
import com.student_usecase.studentportal_db.StudentAcademicsId;

/**
 * Service object for domain model class {@link StudentAcademics}.
 */
public interface StudentAcademicsService {

    /**
     * Creates a new StudentAcademics. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on StudentAcademics if any.
     *
     * @param studentAcademics Details of the StudentAcademics to be created; value cannot be null.
     * @return The newly created StudentAcademics.
     */
    StudentAcademics create(@Valid StudentAcademics studentAcademics);


	/**
     * Returns StudentAcademics by given id if exists.
     *
     * @param studentacademicsId The id of the StudentAcademics to get; value cannot be null.
     * @return StudentAcademics associated with the given studentacademicsId.
	 * @throws EntityNotFoundException If no StudentAcademics is found.
     */
    StudentAcademics getById(StudentAcademicsId studentacademicsId);

    /**
     * Find and return the StudentAcademics by given id if exists, returns null otherwise.
     *
     * @param studentacademicsId The id of the StudentAcademics to get; value cannot be null.
     * @return StudentAcademics associated with the given studentacademicsId.
     */
    StudentAcademics findById(StudentAcademicsId studentacademicsId);

	/**
     * Find and return the list of StudentAcademics by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param studentacademicsIds The id's of the StudentAcademics to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return StudentAcademics associated with the given studentacademicsIds.
     */
    List<StudentAcademics> findByMultipleIds(List<StudentAcademicsId> studentacademicsIds, boolean orderedReturn);


    /**
     * Updates the details of an existing StudentAcademics. It replaces all fields of the existing StudentAcademics with the given studentAcademics.
     *
     * This method overrides the input field values using Server side or database managed properties defined on StudentAcademics if any.
     *
     * @param studentAcademics The details of the StudentAcademics to be updated; value cannot be null.
     * @return The updated StudentAcademics.
     * @throws EntityNotFoundException if no StudentAcademics is found with given input.
     */
    StudentAcademics update(@Valid StudentAcademics studentAcademics);


    /**
     * Partially updates the details of an existing StudentAcademics. It updates only the
     * fields of the existing StudentAcademics which are passed in the studentAcademicsPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on StudentAcademics if any.
     *
     * @param studentacademicsId The id of the StudentAcademics to be deleted; value cannot be null.
     * @param studentAcademicsPatch The partial data of StudentAcademics which is supposed to be updated; value cannot be null.
     * @return The updated StudentAcademics.
     * @throws EntityNotFoundException if no StudentAcademics is found with given input.
     */
    StudentAcademics partialUpdate(StudentAcademicsId studentacademicsId, Map<String, Object> studentAcademicsPatch);

    /**
     * Deletes an existing StudentAcademics with the given id.
     *
     * @param studentacademicsId The id of the StudentAcademics to be deleted; value cannot be null.
     * @return The deleted StudentAcademics.
     * @throws EntityNotFoundException if no StudentAcademics found with the given id.
     */
    StudentAcademics delete(StudentAcademicsId studentacademicsId);

    /**
     * Deletes an existing StudentAcademics with the given object.
     *
     * @param studentAcademics The instance of the StudentAcademics to be deleted; value cannot be null.
     */
    void delete(StudentAcademics studentAcademics);

    /**
     * Find all StudentAcademics matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching StudentAcademics.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<StudentAcademics> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all StudentAcademics matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching StudentAcademics.
     *
     * @see Pageable
     * @see Page
     */
    Page<StudentAcademics> findAll(String query, Pageable pageable);

    /**
     * Exports all StudentAcademics matching the given input query to the given exportType format.
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
     * Exports all StudentAcademics matching the given input query to the given exportType format.
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
     * Retrieve the count of the StudentAcademics in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the StudentAcademics.
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