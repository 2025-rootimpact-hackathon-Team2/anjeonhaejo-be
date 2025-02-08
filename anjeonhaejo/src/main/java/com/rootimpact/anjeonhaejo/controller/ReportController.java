package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.requestDTO.CreateReportRequestDTO;
import com.rootimpact.anjeonhaejo.responseDTO.CreateReportResponseDTO;
import com.rootimpact.anjeonhaejo.security.custom.CustomUserDetails;
import com.rootimpact.anjeonhaejo.service.ReportService;
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

    @PostMapping("/create")
    public String addRecord(@RequestBody CreateReportRequestDTO dto, @AuthenticationPrincipal CustomUserDetails user) throws ChangeSetPersister.NotFoundException {
//        System.out.println("===============" + user.getUserId());
        return reportService.createReport(user.getUserId(), dto);
    }

    @GetMapping("/show/detail/{reportId}")
    public ResponseEntity<CreateReportResponseDTO> showDetailReport(@PathVariable("reportId") Long reportId, @AuthenticationPrincipal CustomUserDetails user) {
//        System.out.println("==============" + user.getUserId()+", reportId" + reportId);
        return reportService.showDetailReport(reportId, user.getUserId());
    }

}
