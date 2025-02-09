package com.rootimpact.anjeonhaejo.requestDTO;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateReportRequestDTO {

    private String title;

    private String content;

    private Long workerLineId;

    private List<Long> tagIds;  // 태그 ID 목록

}
