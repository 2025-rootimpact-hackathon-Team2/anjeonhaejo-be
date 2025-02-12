package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.responseDTO.EmergencyDecibelResponseDTO;
import com.rootimpact.anjeonhaejo.service.AudioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/audio")
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @Operation(summary = "오디오 생성", description = "오디오를 업로드합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "오디오 업로드 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PostMapping("/upload")
    public ResponseEntity<EmergencyDecibelResponseDTO> uploadAudio(
            @Parameter(description = "업로드할 오디오 파일", required = true, content = @Content(mediaType = "multipart/form-data"))
            @RequestParam("file") MultipartFile file,
            @RequestParam("decibel") double decibel,
            @RequestParam("workerZone") Long workerZone
    ) {
        try {
            EmergencyDecibelResponseDTO result = audioService.analyzeAudio(file, decibel, workerZone);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Operation(summary = "전체 오디오 조회", description = "전체 오디오를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "오디오 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @GetMapping("/all")
    public ResponseEntity<List<EmergencyDecibelResponseDTO>> getAllAudioFiles() {
        return ResponseEntity.ok(audioService.getAllAudioFiles());
    }

}
