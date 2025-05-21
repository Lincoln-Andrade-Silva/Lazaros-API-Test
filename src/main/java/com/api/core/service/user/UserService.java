package com.api.core.service.user;

import com.api.core.domain.common.UserMessages;
import com.api.core.domain.dto.user.UserRequest;
import com.api.core.domain.dto.user.UserResponse;
import com.api.core.domain.model.Profile;
import com.api.core.domain.model.User;
import com.api.core.domain.repository.ProfileRepository;
import com.api.core.domain.repository.UserRepository;
import com.api.core.domain.validator.UserValidator;
import com.api.core.mapper.user.UserMapper;
import com.api.util.exception.ApplicationBusinessException;
import com.api.util.response.DataListResponse;
import com.api.util.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public DataListResponse<UserResponse> list() {
        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = users.stream()
                .map(UserMapper::toResponse)
                .sorted((u1, u2) -> Long.compare(u2.getId(), u1.getId()))
                .toList();

        return new DataListResponse<>(userResponses, 0, (long) userResponses.size());
    }

    @Override
    public DataResponse<UserResponse> create(UserRequest request) throws ApplicationBusinessException {
        UserValidator.validateName(request.getName());
        UserValidator.validateProfiles(request.getProfileIds());

        List<Profile> profiles = profileRepository.findAllById(request.getProfileIds());
        UserValidator.validateProfilesExistence(request.getProfileIds(), profiles);

        User user = UserMapper.toEntity(request, profiles);
        user = userRepository.save(user);

        return new DataResponse<>(UserMapper.toResponse(user));
    }

    @Override
    public DataResponse<UserResponse> edit(Long userId, UserRequest request) throws ApplicationBusinessException {
        UserValidator.validateName(request.getName());
        UserValidator.validateProfiles(request.getProfileIds());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationBusinessException(
                        404,
                        UserMessages.USER_NOT_FOUND,
                        String.format(UserMessages.USER_NOT_FOUND_BY_ID, userId),
                        ""
                ));

        var profiles = profileRepository.findAllById(request.getProfileIds());
        UserValidator.validateProfilesExistence(request.getProfileIds(), profiles);

        user.setName(request.getName());
        user.setProfiles(profiles);
        user = userRepository.save(user);

        return new DataResponse<>(UserMapper.toResponse(user));
    }

    @Override
    public void delete(Long userId) throws ApplicationBusinessException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationBusinessException(
                        404,
                        UserMessages.USER_NOT_FOUND,
                        String.format(UserMessages.USER_NOT_FOUND_BY_ID, userId),
                        ""
                ));

        userRepository.deleteById(user.getId());
    }
}
