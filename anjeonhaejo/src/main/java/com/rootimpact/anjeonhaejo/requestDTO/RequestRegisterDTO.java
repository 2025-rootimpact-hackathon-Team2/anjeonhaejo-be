package com.rootimpact.anjeonhaejo.requestDTO;

import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RequestRegisterDTO {

    @Schema(description = "사용자 이름", example = "4테스트")
    private String username;

    @Schema(description = "사용자 상태", example = "작업 중")
    private String userState;

    @Schema(description = "로그인 비밀번호", example = "1234")
    private String password;

    @Schema(description = "로그인 아이디(이메일)", example = "user2@example.com")
    private String email;

    @Schema(description = "사용자 직위", example = "WORKER")
    private RoleType role;

    @Schema(description = "태스크 매니저", example = "작업")
    private String taskManager;

    @Schema(description = "작업 라인 아이디", example = "3")
    private Long workerLineId;



}
