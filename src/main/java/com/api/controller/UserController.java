package com.api.controller;

import com.api.core.domain.common.UserMessages;
import com.api.core.domain.dto.user.UserRequest;
import com.api.core.domain.dto.user.UserResponse;
import com.api.core.service.user.IUserService;
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
@RequestMapping(value = "users")
@CrossOrigin(origins = "${api.access.control.allow.origin}")
@Tag(name = "User Controller", description = "Endpoints of User Controller")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String STARTED = " - Started";
    private static final String FINISHED = " - Finished";

    private final IUserService service;

    @GetMapping
    @Operation(summary = "List Users", description = "List all Users from DB")
    public DataListResponse<UserResponse> list() throws ApplicationBusinessException {
        long startTime = System.currentTimeMillis();
        log.info("List method" + STARTED);

        DataListResponse<UserResponse> response = service.list();
        response.setMessage(UserMessages.USER_LISTED_SUCCESS);

        long endTime = System.currentTimeMillis();
        log.info("List method" + FINISHED + " in " + (endTime - startTime) + "ms");
        return response;
    }

    @PostMapping
    @Operation(summary = "Create User", description = "Create a new User")
    public DataResponse<UserResponse> create(@RequestBody UserRequest request)
            throws ApplicationBusinessException {
        long startTime = System.currentTimeMillis();
        log.info("Create method" + STARTED);
        log.debug("Create method params: {}", request);

        DataResponse<UserResponse> response = service.create(request);
        response.setMessage(UserMessages.USER_CREATED_SUCCESS);

        long endTime = System.currentTimeMillis();
        log.info("Create method" + FINISHED + " in " + (endTime - startTime) + "ms");
        return response;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit User", description = "Edit an existing User")
    public DataResponse<UserResponse> edit(@PathVariable Long id, @RequestBody UserRequest request)
            throws ApplicationBusinessException {
        long startTime = System.currentTimeMillis();
        log.info("Edit method" + STARTED);
        log.debug("Edit method params, ID: {}, Request: {}", id, request);

        DataResponse<UserResponse> response = service.edit(id, request);
        response.setMessage(UserMessages.USER_EDITED_SUCCESS);

        long endTime = System.currentTimeMillis();
        log.info("Edit method" + FINISHED + " in " + (endTime - startTime) + "ms");
        return response;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User", description = "Delete a User by ID")
    public void delete(@PathVariable Long id) throws ApplicationBusinessException {
        long startTime = System.currentTimeMillis();
        log.info("Delete method" + STARTED);
        log.debug("Delete method params, ID: {}", id);

        service.delete(id);

        long endTime = System.currentTimeMillis();
        log.info("Delete method" + FINISHED + " in " + (endTime - startTime) + "ms");
    }
}