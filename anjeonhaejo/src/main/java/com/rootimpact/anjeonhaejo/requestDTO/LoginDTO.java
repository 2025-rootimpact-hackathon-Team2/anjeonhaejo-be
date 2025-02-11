package com.rootimpact.anjeonhaejo.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginDTO {

    @Schema(description = "로그인 아이디(이메일)", example = "user2@example.com")
    private String email;

    @Schema(description = "로그인 비밀번호", example = "1234")
    private String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
