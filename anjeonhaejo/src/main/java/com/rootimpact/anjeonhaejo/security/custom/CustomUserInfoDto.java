package com.rootimpact.anjeonhaejo.security.custom;


import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomUserInfoDto {
    private Long userId;

    private String username;

    private String password;

    private RoleType role;

}
