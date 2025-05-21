package com.api.core.domain.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String name;
    private List<Long> profileIds;
}