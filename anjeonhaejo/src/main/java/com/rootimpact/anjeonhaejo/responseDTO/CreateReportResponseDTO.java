package com.rootimpact.anjeonhaejo.responseDTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateReportResponseDTO {

    private LocalDateTime createdAt;

    private String zoneName;

    private String content;

    private List<String> tagNames;

    @Builder
    public CreateReportResponseDTO(LocalDateTime createdAt, String zoneName, String content, List<String> tagNames) {
        this.createdAt = createdAt;
        this.zoneName = zoneName;
        this.content = content;
        this.tagNames = tagNames;
    }
}
