package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.requestDTO.CreateReportRequestDTO;
import com.rootimpact.anjeonhaejo.responseDTO.CreateReportResponseDTO;
import com.rootimpact.anjeonhaejo.responseDTO.ShowAllReportsTotalResponse;
import com.rootimpact.anjeonhaejo.security.custom.CustomUserDetails;
import com.rootimpact.anjeonhaejo.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.management.LockInfo;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
@Validated
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "리포트 생성", description = "리포트를 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "리포트 추가 성공"),
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

    @Operation(summary = "이번 달 안전 보고서 페이지(3개씩) 줍니다 ~", description = "이번 달 안전 보고서 페이지를 줄게요 ~")
    @GetMapping("")
    public ResponseEntity<List<ShowAllReportsTotalResponse>> getAllReports(
            @RequestParam(name = "page", defaultValue = "0")
            @PositiveOrZero(message = "페이지 수는 0이상인 정수만 가능합니다.") final int page
    ) {
        return ResponseEntity.ok(reportService.showAllReportsTotal(page));
    }
}
