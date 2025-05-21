package com.api.core.service.profile;

import com.api.core.domain.dto.profile.ProfileRequest;
import com.api.core.domain.dto.profile.ProfileResponse;
import com.api.util.exception.ApplicationBusinessException;
import com.api.util.response.DataListResponse;
import com.api.util.response.DataResponse;

public interface IProfileService {
    DataListResponse<ProfileResponse> list();

    DataResponse<ProfileResponse> create(ProfileRequest request) throws ApplicationBusinessException;

    DataResponse<ProfileResponse> edit(Long profileId, ProfileRequest request) throws ApplicationBusinessException;

    void delete(Long profileId) throws ApplicationBusinessException;
}
