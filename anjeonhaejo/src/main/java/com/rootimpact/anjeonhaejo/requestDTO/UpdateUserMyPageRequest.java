package com.rootimpact.anjeonhaejo.requestDTO;

import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;
import jakarta.annotation.Nullable;

public record UpdateUserMyPageRequest(

        String name,

        String factory,

        RoleType role,

        String department
) {
}
