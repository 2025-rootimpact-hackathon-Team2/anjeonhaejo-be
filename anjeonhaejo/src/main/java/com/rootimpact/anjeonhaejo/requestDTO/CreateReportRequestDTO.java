package com.rootimpact.anjeonhaejo.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateReportRequestDTO {

    @Schema(description = "컨텐츠", example = "아무 말이나 주세요 ..")
    private String content;

    @Schema(description = "작업 라인 아이디", example = "1(A)")
    private Long workerLineId;

    @Schema(description = "상태", example = "긴급조치필요")
    private String status;

    @Schema(description = "태그 이름 목록", example = "[기계소음이상, 누수발견, 환기불량]")
    private List<String> tagNames;  // 태그 이름 목록

}
