package com.rootimpact.anjeonhaejo.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AddTagDTO {

    @Schema(description = "태그 이름", example = "소음 이상")
    private String tagName;

    @Schema(description = "태그 카테고리", example = "기계")
    private String category;

    public AddTagDTO(String tagName, String category) {
        this.tagName = tagName;
        this.category = category;
    }
}
