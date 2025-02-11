package com.rootimpact.anjeonhaejo.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateReportRequestDTO {

    @Schema(description = "리포트 제목", example = "222")
    private String title;

    @Schema(description = "컨텐츠", example = "22")
    private String content;

    @Schema(description = "작업 라인 아이디", example = "3")
    private Long workerLineId;

    @Schema(description = "태그 아이디 목록", example = "[2]")
    private List<Long> tagIds;  // 태그 ID 목록

}
