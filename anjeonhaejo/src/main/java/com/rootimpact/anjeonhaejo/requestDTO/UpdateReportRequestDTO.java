package com.rootimpact.anjeonhaejo.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateReportRequestDTO {

    @Schema(description = "수정할 내용", example = "장비 누수, 밸브 교체 필요")
    private String content;

    @Schema(description = "수정할 상태", example = "RESOLVED")
    private String status;

    @Schema(description = "새 태그 이름 목록", example = "[\"기타\", \"중요\"]")
    private List<String> tagNames;
}