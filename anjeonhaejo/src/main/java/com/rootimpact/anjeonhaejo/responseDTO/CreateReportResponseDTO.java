package com.rootimpact.anjeonhaejo.responseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateReportResponseDTO {

    @Schema(description = "시간", example = "2024-02-06 00:00:01")
    private LocalDateTime createdAt;

    @Schema(description = "구역 이름", example = "A 구역")
    private String zoneName;

    @Schema(description = "컨텐츠", example = "22")
    private String content;

    @Schema(description = "태그 목록", example = "[2]")
    private List<String> tagNames;

    @Builder
    public CreateReportResponseDTO(LocalDateTime createdAt, String zoneName, String content, List<String> tagNames) {
        this.createdAt = createdAt;
        this.zoneName = zoneName;
        this.content = content;
        this.tagNames = tagNames;
    }
}
