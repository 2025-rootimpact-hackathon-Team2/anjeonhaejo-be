package com.rootimpact.anjeonhaejo.requestDTO;

import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;
import jakarta.annotation.Nullable;

public record UpdateUserMyPageRequest(

        @Nullable
        String name,

        @Nullable
        String factory,

        @Nullable
        RoleType role,

        @Nullable
        String department
) {
}
