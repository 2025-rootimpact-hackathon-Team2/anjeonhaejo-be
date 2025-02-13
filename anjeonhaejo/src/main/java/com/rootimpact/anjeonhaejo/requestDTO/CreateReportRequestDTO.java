package com.rootimpact.anjeonhaejo.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateReportRequestDTO {

    @Schema(description = "컨텐츠", example = "22")
    private String content;

    @Schema(description = "작업 라인 아이디", example = "1(A)")
    private Long workerLineId;

    @Schema(description = "태그 이름 목록", example = "[]")
    private List<String> tagNames;  // 태그 이름 목록

}
