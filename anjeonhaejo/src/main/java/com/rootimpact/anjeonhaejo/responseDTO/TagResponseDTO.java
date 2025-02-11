package com.rootimpact.anjeonhaejo.responseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class TagResponseDTO {

    @Schema(description = "태그 이름", example = "소음 이상")
    private String name;

    public TagResponseDTO(String name) {
        this.name = name;
    }
}
