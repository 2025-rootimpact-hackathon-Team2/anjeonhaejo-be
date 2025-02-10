package com.rootimpact.anjeonhaejo.responseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
public class ShowAllTageDTO {

    private List<String> tagName;

    private String categoryName;

    public ShowAllTageDTO(List<String> tagName, String categoryName) {
        this.tagName = tagName;
        this.categoryName = categoryName;
    }
}
