package com.docker.dev.training.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private Long id;
    private String username;
    private String password;
}
