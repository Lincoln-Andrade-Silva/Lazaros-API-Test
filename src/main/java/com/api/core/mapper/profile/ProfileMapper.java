package com.api.core.mapper.profile;

import com.api.core.domain.dto.profile.ProfileRequest;
import com.api.core.domain.dto.profile.ProfileResponse;
import com.api.core.domain.model.Profile;

public class ProfileMapper {

    private ProfileMapper() {
    }

    public static ProfileResponse toResponse(Profile profile) {
        return ProfileResponse.builder().id(profile.getId()).description(profile.getDescription()).build();
    }

    public static Profile toEntity(ProfileRequest request) {
        return Profile.builder().description(request.getDescription()).build();
    }

}
