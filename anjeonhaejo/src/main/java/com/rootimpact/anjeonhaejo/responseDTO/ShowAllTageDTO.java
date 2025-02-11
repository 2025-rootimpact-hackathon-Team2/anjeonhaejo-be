package com.rootimpact.anjeonhaejo.responseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
public class ShowAllTageDTO {

    @Schema(description = "태그 이름", example = "소음이상")
    private List<String> tagName;

    @Schema(description = "태그 카테고리", example = "기계")
    private String categoryName;

    public ShowAllTageDTO(List<String> tagName, String categoryName) {
        this.tagName = tagName;
        this.categoryName = categoryName;
    }
}
