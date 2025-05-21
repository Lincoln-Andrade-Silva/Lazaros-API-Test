package com.api.core.domain.dto.user;

import com.api.core.domain.dto.GenericDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private List<GenericDTO> profiles;
}