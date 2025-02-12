package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.domain.Report;
import com.rootimpact.anjeonhaejo.domain.Tag;
import com.rootimpact.anjeonhaejo.domain.User;
import com.rootimpact.anjeonhaejo.domain.WorkerLine;
import com.rootimpact.anjeonhaejo.repository.ReportRepository;
import com.rootimpact.anjeonhaejo.repository.TagRepository;
import com.rootimpact.anjeonhaejo.repository.UserRepository;
import com.rootimpact.anjeonhaejo.repository.WorkerLineRepository;
import com.rootimpact.anjeonhaejo.requestDTO.CreateReportRequestDTO;
import com.rootimpact.anjeonhaejo.responseDTO.CreateReportResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final WorkerLineRepository wlRepository;
    private final TagRepository tagRepository;  // TagRepository 추가

    @Transactional
    public String createReport(Long userId, CreateReportRequestDTO dto) {

        WorkerLine workerLine = wlRepository.findById(dto.getWorkerLineId()).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (workerLine != null && user != null) {
            Report report = new Report(
                    dto.getContent(),
                    user,
                    workerLine
            );

            // 태그 추가
            for (Long tagId : dto.getTagIds()) {
                Tag tag = tagRepository.findById(tagId).orElse(null);
                if (tag != null) {
                    report.addTag(tag);  // 태그 추가
                }
            }

            reportRepository.save(report);
            return "Good";
        } else {
            return "Bad";
        }
    }

    @Transactional
    public ResponseEntity<CreateReportResponseDTO> showDetailReport(Long reportId, Long userId) {
        Report report = reportRepository.findById(reportId).orElse(null);

        // 태그 이름 목록을 가져오기
        List<String> tagNames = report.getTagMap().stream()  // getTagMap()을 사용하여 태그를 가져옴
                .map(reportTagMap -> reportTagMap.getTag() != null ? reportTagMap.getTag().getName() : "")  // tag에 직접 접근하여 이름을 추출
                .collect(Collectors.toList());

        // DTO 생성
        CreateReportResponseDTO dto = CreateReportResponseDTO.builder()
                .createdAt(report.getCreateTime())  // 생성된 시간
                .zoneName(report.getWorkerLine().getZoneName())  // 작업 라인 구역명
                .content(report.getContent())  // 리포트 내용
                .tagNames(tagNames)  // 태그 이름 목록
                .build();

        return ResponseEntity.ok(dto);
    }





}

