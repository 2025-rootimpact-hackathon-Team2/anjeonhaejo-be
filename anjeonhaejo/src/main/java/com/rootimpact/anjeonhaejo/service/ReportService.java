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
import com.rootimpact.anjeonhaejo.responseDTO.ShowAllReportsTotalResponse;
import com.rootimpact.anjeonhaejo.responseDTO.ShowAllReportsWithTotalPageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public Long createReport(Long userId, CreateReportRequestDTO dto) {
        WorkerLine workerLine = wlRepository.findById(dto.getWorkerLineId())
                .orElseThrow(() -> new IllegalArgumentException("작업 라인을 찾을 수 없습니다. ID: " + dto.getWorkerLineId()));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));

        // 1. 먼저 Report 저장
        Report report = Report.builder()
                .content(dto.getContent())
                .title(dto.getTagNames().getFirst())
                .user(user)
                .workerLine(workerLine)
                .status("즉각 조치 필요")
                .build();

        report = reportRepository.save(report); // ✅ 먼저 저장

        // 2. 태그 추가 후 저장
        Set<String> uniqueTags = new HashSet<>(dto.getTagNames()); // 중복 방지
        for (String tagName : uniqueTags) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> tagRepository.save(new Tag(tagName, "")));

            report.addTag(tag);
        }

        reportRepository.save(report); // ✅ 태그 추가 후 다시 저장

        return report.getId();
    }


    @Transactional
    public ResponseEntity<CreateReportResponseDTO> showDetailReport(Long reportId, Long userId) {
        Report report = reportRepository.findById(reportId).orElse(null);

        // 태그 이름 목록을 가져오기
        List<String> tagNames = report.getReportTags().stream()  // getTagMap()을 사용하여 태그를 가져옴
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

    @Transactional
    public ShowAllReportsWithTotalPageResponse showAllReportsWithTotalPage(int page) {
        Pageable pageable = PageRequest.of(page, 3);

        Page<Report> reportsPage = reportRepository.findAllByOrderByCreateTimeDesc(pageable);

        int totalPage = reportsPage.getTotalPages();

        List<ShowAllReportsTotalResponse> reports = reportsPage.getContent().stream()
                .map(ShowAllReportsTotalResponse::from)
                .toList();

        return ShowAllReportsWithTotalPageResponse.of(totalPage, reports);
    }
}
