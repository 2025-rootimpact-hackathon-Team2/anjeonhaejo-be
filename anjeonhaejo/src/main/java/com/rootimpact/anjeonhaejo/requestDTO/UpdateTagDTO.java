package com.rootimpact.anjeonhaejo.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UpdateTagDTO {

    @Schema(description = "새로운 태그 이름", example = "소음 이상")
    private String newTageName;

    @Schema(description = "새로운 태그 이름", example = "기계")
    private String newCategory;

    public UpdateTagDTO(String newTageName, String newCategory) {
        this.newTageName = newTageName;
        this.newCategory = newCategory;
    }
}
