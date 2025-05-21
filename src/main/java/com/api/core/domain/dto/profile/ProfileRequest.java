package com.api.core.domain.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {

    @NotBlank(message = "Description is mandatory")
    @Size(min = 5, message = "Description must have at least 5 characters")
    private String description;
}
