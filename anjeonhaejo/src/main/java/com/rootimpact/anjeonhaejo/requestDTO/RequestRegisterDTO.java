package com.rootimpact.anjeonhaejo.requestDTO;

import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RequestRegisterDTO {

    private String username;

    private String userState;

    private String password;

    private String email;

    private RoleType role;

    private String taskManager;

    private Long workerLineId;



}
