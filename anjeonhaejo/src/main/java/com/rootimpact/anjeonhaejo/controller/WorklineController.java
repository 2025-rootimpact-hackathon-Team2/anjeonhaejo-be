package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.responseDTO.EmergencyDecibelResponseDTO;
import com.rootimpact.anjeonhaejo.responseDTO.ShowStateResponseDTO;
import com.rootimpact.anjeonhaejo.service.WorklineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workline")
public class WorklineController {

    private final WorklineService worklineService;

    @Operation(summary = "노이즈 상태 조회", description = "노이즈 상태를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "노이즈 상태 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @GetMapping("/show/state")
    private ResponseEntity<List<ShowStateResponseDTO>> showNoiseState() {
        return worklineService.showNoiseState();
    }

}
