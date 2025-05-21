package com.api.core.service.profile;

import com.api.core.domain.common.ProfileMessages;
import com.api.core.domain.dto.profile.ProfileRequest;
import com.api.core.domain.dto.profile.ProfileResponse;
import com.api.core.domain.model.Profile;
import com.api.core.domain.repository.ProfileRepository;
import com.api.core.domain.validator.ProfileValidator;
import com.api.core.mapper.profile.ProfileMapper;
import com.api.util.exception.ApplicationBusinessException;
import com.api.util.response.DataListResponse;
import com.api.util.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    private final ProfileRepository repository;

    @Override
    public DataListResponse<ProfileResponse> list() {
        List<Profile> profiles = repository.findAll();

        List<ProfileResponse> profileResponses = profiles.stream()
                .map(ProfileMapper::toResponse)
                .sorted((p1, p2) -> Long.compare(p2.getId(), p1.getId()))
                .toList();

        return new DataListResponse<>(profileResponses, 0, (long) profileResponses.size());
    }

    @Override
    public DataResponse<ProfileResponse> create(ProfileRequest request) throws ApplicationBusinessException {
        ProfileValidator.validateDescription(request.getDescription());

        Profile profile = ProfileMapper.toEntity(request);
        profile = repository.save(profile);

        return new DataResponse<>(ProfileMapper.toResponse(profile));
    }

    @Override
    public DataResponse<ProfileResponse> edit(Long profileId, ProfileRequest request) throws ApplicationBusinessException {
        ProfileValidator.validateDescription(request.getDescription());

        Profile profile = repository.findById(profileId)
                .orElseThrow(() -> new ApplicationBusinessException(
                        404,
                        ProfileMessages.PROFILE_NOT_FOUND,
                        String.format(ProfileMessages.PROFILE_NOT_FOUND_BY_ID, profileId),
                        ""
                ));

        profile.setDescription(request.getDescription());
        profile = repository.save(profile);

        return new DataResponse<>(ProfileMapper.toResponse(profile));
    }

    @Override
    public void delete(Long profileId) throws ApplicationBusinessException {
        Profile profile = repository.findById(profileId)
                .orElseThrow(() -> new ApplicationBusinessException(
                        404,
                        ProfileMessages.PROFILE_NOT_FOUND,
                        String.format(ProfileMessages.PROFILE_NOT_FOUND_BY_ID, profileId),
                        ""
                ));

        repository.deleteById(profile.getId());
    }
}