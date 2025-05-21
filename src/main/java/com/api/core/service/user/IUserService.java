package com.api.core.service.user;

import com.api.core.domain.dto.user.UserRequest;
import com.api.core.domain.dto.user.UserResponse;
import com.api.util.exception.ApplicationBusinessException;
import com.api.util.response.DataListResponse;
import com.api.util.response.DataResponse;

public interface IUserService {
    DataListResponse<UserResponse> list();

    DataResponse<UserResponse> create(UserRequest request) throws ApplicationBusinessException;

    DataResponse<UserResponse> edit(Long userId, UserRequest request) throws ApplicationBusinessException;

    void delete(Long userId) throws ApplicationBusinessException;
}
