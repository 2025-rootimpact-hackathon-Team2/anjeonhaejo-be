package com.rootimpact.anjeonhaejo.responseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@Builder
public class ShowAllTageDTO {
    @Schema(description = "태그 목록 (이름 -> ID)", example = "{\"소음 이상\": 1, \"기계 소리 이상\": 2}")
    private Map<String, Long> tagMap; // 태그 이름과 ID를 매핑한 Map

    @Schema(description = "카테고리 이름", example = "기계")
    private String categoryName;

    public ShowAllTageDTO(Map<String, Long> tagMap, String categoryName) {
        this.tagMap = tagMap;
        this.categoryName = categoryName;
    }
}
