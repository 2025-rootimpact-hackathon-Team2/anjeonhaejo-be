package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.responseDTO.noise.GetMaxDecibelResponse;
import com.rootimpact.anjeonhaejo.responseDTO.noise.GetMinDecibelResponse;
import com.rootimpact.anjeonhaejo.responseDTO.noise.GetMonthAvgDecibelResponse;
import com.rootimpact.anjeonhaejo.service.NoiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/noise")
@RequiredArgsConstructor
public class NoiseController {

    private final NoiseService noiseService;

    @Operation(summary = "월 평균 데시벨 조회", description = "특정 월의 평균 소음 데시벨을 조회합니다.")
    @GetMapping("/average")
    public ResponseEntity<GetMonthAvgDecibelResponse> getMonthAvgDecibel(
            @Parameter(description = "조회할 날짜 (yyyy-MM-dd 형식)", example = "2024-02-12")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(noiseService.showMonthAvgDecibelResponse(date));
    }

    @Operation(summary = "최대 데시벨 조회", description = "특정 월의 최대 소음 데시벨을 조회합니다.")
    @GetMapping("/max")
    public ResponseEntity<GetMaxDecibelResponse> getMaxDecibel(
            @Parameter(description = "조회할 날짜 (yyyy-MM-dd 형식)", example = "2024-02-12")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(noiseService.showMaxDecibelResponse(date));
    }

    @Operation(summary = "최소 데시벨 조회", description = "특정 월의 최소 소음 데시벨을 조회합니다.")
    @GetMapping("/min")
    public ResponseEntity<GetMinDecibelResponse> getMinDecibel(
            @Parameter(description = "조회할 날짜 (yyyy-MM-dd 형식)", example = "2024-02-12")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(noiseService.showMinDecibelResponse(date));
    }
}
