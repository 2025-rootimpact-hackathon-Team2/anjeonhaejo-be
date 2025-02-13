package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.requestDTO.UpdateUserMyPageRequest;
import com.rootimpact.anjeonhaejo.responseDTO.ReadUserMyPageResponse;
import com.rootimpact.anjeonhaejo.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @GetMapping("/mypage/{userId}")
    public ResponseEntity<ReadUserMyPageResponse> readUserMyPage(@PathVariable Long userId) {
        return ResponseEntity.ok(settingService.showMyPage(userId));
    }

    @PutMapping("/mypage/{userId}")
    public ResponseEntity<Void> updateUserMyPage(
            @PathVariable Long userId,
            @RequestBody UpdateUserMyPageRequest request) {
        Long id = settingService.modifyUserMyPage(userId, request);
        return ResponseEntity.created(URI.create("/api/v1/mypage/" + id)).build();
    }
}
