package com.api.core.domain.validator;

import com.api.core.domain.common.UserMessages;
import com.api.util.exception.ApplicationBusinessException;

import java.util.List;

public class UserValidator {

    private UserValidator() {
    }

    public static void validateName(String name) throws ApplicationBusinessException {
        if (name == null || name.trim().isEmpty()) {
            throw new ApplicationBusinessException(
                    400,
                    "Name is mandatory",
                    "The field 'name' must not be null or empty.",
                    ""
            );
        }
        if (name.trim().length() < 10) {
            throw new ApplicationBusinessException(
                    400,
                    "Name is too short",
                    "The field 'name' must have at least 10 characters.",
                    ""
            );
        }
    }

    public static void validateProfiles(List<?> profiles) throws ApplicationBusinessException {
        if (profiles == null || profiles.isEmpty()) {
            throw new ApplicationBusinessException(
                    400,
                    "Profiles are mandatory",
                    "User must have at least one profile.",
                    ""
            );
        }
    }

    public static void validateProfilesExistence(List<Long> requestedProfileIds, List<?> foundProfiles)
            throws ApplicationBusinessException {
        if (foundProfiles.size() != requestedProfileIds.size()) {
            throw new ApplicationBusinessException(
                    400,
                    "Invalid Profiles",
                    "One or more profile IDs are invalid",
                    ""
            );
        }
    }

}