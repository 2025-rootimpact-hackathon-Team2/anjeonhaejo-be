package com.rootimpact.anjeonhaejo.requestDTO;

import lombok.Getter;

@Getter
public class CreateReportRequestDTO {

    private String title;

    private String content;

    private Long workerLineId;

}
