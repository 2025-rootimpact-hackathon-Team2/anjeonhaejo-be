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
            for (String tagName : dto.getTagNames()) {
                Tag tag = tagRepository.findByName(tagName)
                        .orElseThrow(null);
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

    @Transactional
    public List<ShowAllReportsTotalResponse> showAllReportsTotal(int page) {
        Pageable pageable = PageRequest.of(page, 3);

        Page<Report> reportsPage = reportRepository.findAllByOrderByCreateTimeDesc(pageable);

        return reportsPage.getContent().stream()
                .map(ShowAllReportsTotalResponse::from)
                .toList();

//        return reportRepository.findAllByOrderByCreateTimeDesc(pageable)
//                .stream()
//                .map(ShowAllReportsTotalResponse::from)
//                .toList();
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
