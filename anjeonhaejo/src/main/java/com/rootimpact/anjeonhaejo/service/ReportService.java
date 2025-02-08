package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.domain.Report;
import com.rootimpact.anjeonhaejo.domain.User;
import com.rootimpact.anjeonhaejo.domain.WorkerLine;
import com.rootimpact.anjeonhaejo.repository.ReportRepository;
import com.rootimpact.anjeonhaejo.repository.UserRepository;
import com.rootimpact.anjeonhaejo.repository.WorkerLineRepository;
import com.rootimpact.anjeonhaejo.requestDTO.CreateReportRequestDTO;
import com.rootimpact.anjeonhaejo.responseDTO.CreateReportResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final WorkerLineRepository wlRepository;

    @Transactional
    public String createReport(Long userId, CreateReportRequestDTO dto) {

        WorkerLine workerLine = wlRepository.findById(dto.getWorkerLineId()).orElse(null);

        User user = userRepository.findById(userId).orElse(null);

        if(workerLine != null && user != null) {
            Report report = new Report(
                    dto.getTitle(),
                    dto.getContent(),
                    user,
                    workerLine
            );
            reportRepository.save(report);
            return "Good";
        }else {
            return "Bad";
        }

    }

    @Transactional
    public ResponseEntity<CreateReportResponseDTO> showDetailReport(Long reportId, Long userId) {
        Report report = reportRepository.findById(reportId).orElse(null);
//        log.info("service"+ report.getContent());

        CreateReportResponseDTO dto = CreateReportResponseDTO.builder()
                    .createdAt(report.getCreateTime())
                    .zoneName(report.getWorkerLine().getZoneName())
                    .content(report.getContent())
                    .build();
        return ResponseEntity.ok(dto);
    }
}
