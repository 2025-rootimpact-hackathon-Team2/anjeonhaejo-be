package com.rootimpact.anjeonhaejo.requestDTO;

import lombok.Getter;

@Getter
public class UpdateTagDTO {

    private String newTageName;

    private String newCategory;

    public UpdateTagDTO(String newTageName, String newCategory) {
        this.newTageName = newTageName;
        this.newCategory = newCategory;
    }
}
