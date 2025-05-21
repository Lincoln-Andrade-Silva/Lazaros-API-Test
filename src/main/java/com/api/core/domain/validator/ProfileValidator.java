package com.api.core.domain.validator;

import com.api.util.exception.ApplicationBusinessException;

public class ProfileValidator {

    private ProfileValidator() {
    }

    public static void validateDescription(String description) throws ApplicationBusinessException {
        if (description == null || description.trim().isEmpty()) {
            throw new ApplicationBusinessException(
                    400,
                    "Description is mandatory",
                    "The field 'description' must not be null or empty.",
                    ""
            );
        }
        if (description.trim().length() < 5) {
            throw new ApplicationBusinessException(
                    400,
                    "Description is too short",
                    "The field 'description' must have at least 5 characters.",
                    ""
            );
        }
    }
}