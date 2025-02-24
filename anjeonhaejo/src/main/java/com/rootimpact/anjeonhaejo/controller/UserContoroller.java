package com.rootimpact.anjeonhaejo.controller;


import com.rootimpact.anjeonhaejo.requestDTO.LoginDTO;
import com.rootimpact.anjeonhaejo.requestDTO.RequestRegisterDTO;
import com.rootimpact.anjeonhaejo.responseDTO.ReadUserMyPageResponse;
import com.rootimpact.anjeonhaejo.security.jwt.JwtAuthFilter;
import com.rootimpact.anjeonhaejo.service.SettingService;
import com.rootimpact.anjeonhaejo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserContoroller {

    private final UserService userService;
    private final SettingService settingService;

    @Operation(summary = "사용자 생성", description = "사용자를 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "사용자 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PostMapping("/register")
    public String register(@RequestBody RequestRegisterDTO registerDTO){
//        System.out.println(registerDTO.getGender());
        userService.register(registerDTO);

        String token = userService.login(new LoginDTO(registerDTO.getUsername(), registerDTO.getPassword()));
        insertToken(token);

        return token;
    }

    @Operation(summary = "사용자 로그인", description = "사용자가 로그인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용자 로그인 성공"),
            @ApiResponse(responseCode = "401", description = "잘못된 요청 데이터")
    })
    @PostMapping("/login")
    public ResponseEntity<ReadUserMyPageResponse> login(@RequestBody LoginDTO dto) {
        log.info("email: {}, password: {}", dto.getEmail(), dto.getPassword());
        String token = userService.login(dto);

        if (Objects.nonNull(settingService.showMyPageByEmail(dto.getEmail()))) {
            return ResponseEntity.ok(settingService.showMyPageByEmail(dto.getEmail()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "사용자 로그아웃", description = "사용자가 로그아웃합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용자 로그아웃 성공"),
            @ApiResponse(responseCode = "401", description = "잘못된 요청 데이터")
    })
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // 쿠키 삭제
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // 즉시 만료
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK).body("Logged out successfully");
    }

    public void insertToken(String token) {
        try {
            // 쿠키 값 UTF-8로 인코딩
            String cookieValue = URLEncoder.encode(token, "UTF-8");
            Cookie cookie = new Cookie("accessToken", cookieValue);

            cookie.setPath("/");
            cookie.setSecure(false); // 실제 배포 시 true로 설정
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30일
            cookie.setHttpOnly(true);

            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            // 인코딩 실패 시 로깅 또는 예외 처리
            System.err.println("쿠키 인코딩 오류: " + e.getMessage());
        }
    }

    // 쿠키 값에 유효한 문자가 포함되어 있는지 확인하는 메서드
    private boolean isValidCookieValue(String value) {
        // 예시: 공백이 아닌 ASCII 32 이상인지 확인
        for (char c : value.toCharArray()) {
            if (c < 32 || c > 126) { // 허용할 ASCII 범위 설정
                return false;
            }
        }
        return true;
    }

}