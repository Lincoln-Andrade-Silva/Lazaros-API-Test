package com.api.core.mapper.user;

import com.api.core.domain.dto.GenericDTO;
import com.api.core.domain.dto.user.UserRequest;
import com.api.core.domain.dto.user.UserResponse;
import com.api.core.domain.model.Profile;
import com.api.core.domain.model.User;

import java.util.List;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserRequest request, List<Profile> profiles) {
        return User.builder()
                .profiles(profiles)
                .name(request.getName())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .profiles(user.getProfiles().stream()
                        .map(p -> new GenericDTO(p.getId(), p.getDescription()))
                        .toList())
                .build();
    }
}
