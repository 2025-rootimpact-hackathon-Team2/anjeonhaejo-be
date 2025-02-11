package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.requestDTO.CreateReportRequestDTO;
import com.rootimpact.anjeonhaejo.responseDTO.CreateReportResponseDTO;
import com.rootimpact.anjeonhaejo.security.custom.CustomUserDetails;
import com.rootimpact.anjeonhaejo.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "레코드 생성", description = "레코드를 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "레코드 추가 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PostMapping("/create")
    public String addRecord(@RequestBody CreateReportRequestDTO dto, @AuthenticationPrincipal CustomUserDetails user) throws ChangeSetPersister.NotFoundException {
        return reportService.createReport(user.getUserId(), dto);
    }

    @Operation(summary = "리포트 조회", description = "단일 상세 리포트를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "리포트 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @GetMapping("/show/detail/{reportId}")
    public ResponseEntity<CreateReportResponseDTO> showDetailReport(@PathVariable("reportId") Long reportId, @AuthenticationPrincipal CustomUserDetails user) {
        return reportService.showDetailReport(reportId, user.getUserId());
    }
}
