package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.requestDTO.UpdateUserMyPageRequest;
import com.rootimpact.anjeonhaejo.responseDTO.ReadUserMyPageResponse;
import com.rootimpact.anjeonhaejo.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Tag(name = "Setting Controller", description = "마이페이지 설정 관련 API")
public class SettingController {

    private final SettingService settingService;

    @Operation(
            summary = "마이페이지 조회",
            description = "사용자의 마이페이지 정보를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공",
                            content = @Content(schema = @Schema(implementation = ReadUserMyPageResponse.class))),
                    @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
            }
    )

    @GetMapping("/mypage/{userId}")
    public ResponseEntity<ReadUserMyPageResponse> readUserMyPage(
            @Parameter(description = "사용자의 ID", example = "1")
            @PathVariable Long userId) {
        return ResponseEntity.ok(settingService.showMyPage(userId));
    }

    @Operation(
            summary = "마이페이지 수정",
            description = "사용자의 마이페이지 정보를 수정합니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "수정 완료"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청 형식"),
                    @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
            }
    )

    @PutMapping("/mypage/{userId}")
    public ResponseEntity<Void> updateUserMyPage(
            @Parameter(description = "사용자의 ID", example = "1")
            @PathVariable Long userId,
            @RequestBody UpdateUserMyPageRequest request) {
        Long id = settingService.modifyUserMyPage(userId, request);
        return ResponseEntity.created(URI.create("/api/v1/mypage/" + id)).build();
    }
}
