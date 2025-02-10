package com.rootimpact.anjeonhaejo.requestDTO;

import lombok.Getter;

@Getter
public class AddTagDTO {

    private String tagName;

    private String category;

    public AddTagDTO(String tagName, String category) {
        this.tagName = tagName;
        this.category = category;
    }
}
