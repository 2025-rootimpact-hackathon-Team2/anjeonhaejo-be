package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.responseDTO.noise.GetMaxDecibelResponse;
import com.rootimpact.anjeonhaejo.responseDTO.noise.GetMinDecibelResponse;
import com.rootimpact.anjeonhaejo.responseDTO.noise.GetMonthAvgDecibelResponse;
import com.rootimpact.anjeonhaejo.service.NoiseService;
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

    // 월 평균 데시벨 조회
    @GetMapping("/average")
    public ResponseEntity<GetMonthAvgDecibelResponse> getMonthAvgDecibel(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(noiseService.showMonthAvgDecibelResponse(date));
    }

    // 최대 데시벨 조회
    @GetMapping("/max")
    public ResponseEntity<GetMaxDecibelResponse> getMaxDecibel(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(noiseService.showMaxDecibelResponse(date));
    }

    // 최소 데시벨 조회
    @GetMapping("/min")
    public ResponseEntity<GetMinDecibelResponse> getMinDecibel(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(noiseService.showMinDecibelResponse(date));
    }
}
