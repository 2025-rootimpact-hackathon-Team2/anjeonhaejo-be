package com.rootimpact.anjeonhaejo.responseDTO;

import lombok.Getter;

@Getter
public class TagResponseDTO {

    private String name;

    public TagResponseDTO(String name) {
        this.name = name;
    }
}
