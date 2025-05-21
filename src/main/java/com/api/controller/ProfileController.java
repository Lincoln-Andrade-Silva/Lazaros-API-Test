package com.api.controller;

import com.api.core.domain.common.ProfileMessages;
import com.api.core.domain.dto.profile.ProfileRequest;
import com.api.core.domain.dto.profile.ProfileResponse;
import com.api.core.service.profile.IProfileService;
import com.api.util.exception.ApplicationBusinessException;
import com.api.util.response.DataListResponse;
import com.api.util.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "profile")
@CrossOrigin(origins = "${api.access.control.allow.origin}")
@Tag(name = "Profile Controller", description = "Endpoints of Profile Controller")
public class ProfileController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private static final String STARTED = " - Started";
    private static final String FINISHED = " - Finished";

    private final IProfileService service;

    @GetMapping
    @Operation(summary = "List Profiles", description = "List all Profiles from DB")
    public DataListResponse<ProfileResponse> list() throws ApplicationBusinessException {
        long startTime = System.currentTimeMillis();
        log.info("List method" + STARTED);

        DataListResponse<ProfileResponse> response = service.list();
        response.setMessage(ProfileMessages.PROFILE_LISTED_SUCCESS);

        long endTime = System.currentTimeMillis();
        log.info("List method" + FINISHED + " in " + (endTime - startTime) + "ms");
        return response;
    }

    @PostMapping
    @Operation(summary = "Create Profile", description = "Create a new Profile")
    public DataResponse<ProfileResponse> create(@RequestBody ProfileRequest request)
            throws ApplicationBusinessException {
        long startTime = System.currentTimeMillis();
        log.info("Create method" + STARTED);
        log.debug("Create method params: {}", request);

        DataResponse<ProfileResponse> response = service.create(request);
        response.setMessage(ProfileMessages.PROFILE_CREATED_SUCCESS);

        long endTime = System.currentTimeMillis();
        log.info("Create method" + FINISHED + " in " + (endTime - startTime) + "ms");
        return response;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit Profile", description = "Edit an existing Profile")
    public DataResponse<ProfileResponse> edit(@PathVariable Long id, @RequestBody ProfileRequest request)
            throws ApplicationBusinessException {
        long startTime = System.currentTimeMillis();
        log.info("Edit method" + STARTED);
        log.debug("Edit method params, ID: {}, Request: {}", id, request);

        DataResponse<ProfileResponse> response = service.edit(id, request);
        response.setMessage(ProfileMessages.PROFILE_EDITED_SUCCESS);

        long endTime = System.currentTimeMillis();
        log.info("Edit method" + FINISHED + " in " + (endTime - startTime) + "ms");
        return response;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Profile", description = "Delete a Profile by ID")
    public void delete(@PathVariable Long id) throws ApplicationBusinessException {
        long startTime = System.currentTimeMillis();
        log.info("Delete method" + STARTED);
        log.debug("Delete method params, ID: {}", id);

        service.delete(id);

        long endTime = System.currentTimeMillis();
        log.info("Delete method" + FINISHED + " in " + (endTime - startTime) + "ms");
    }
}