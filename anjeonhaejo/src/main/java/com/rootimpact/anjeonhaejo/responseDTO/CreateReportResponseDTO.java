package com.rootimpact.anjeonhaejo.responseDTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateReportResponseDTO {

    private LocalDateTime createdAt;

    private String zoneName;

    private String content;

    @Builder
    public CreateReportResponseDTO(LocalDateTime createdAt, String zoneName, String content) {
        this.createdAt = createdAt;
        this.zoneName = zoneName;
        this.content = content;
    }
}
