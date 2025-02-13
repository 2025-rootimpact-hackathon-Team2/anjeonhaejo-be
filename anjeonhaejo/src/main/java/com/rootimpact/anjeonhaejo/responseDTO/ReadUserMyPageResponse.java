package com.rootimpact.anjeonhaejo.responseDTO;

import com.rootimpact.anjeonhaejo.domain.User;
import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;

public record ReadUserMyPageResponse(

        Long id,
        String name,
        String factory,
        RoleType role,
        String department
) {

    public static ReadUserMyPageResponse from(User user) {
        return new ReadUserMyPageResponse(
                user.getId(),
                user.getUsername(),
                user.getFactory(),
                user.getRole(),
                user.getDepartment()
        );
    }
}
