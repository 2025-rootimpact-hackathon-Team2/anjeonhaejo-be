package com.rootimpact.anjeonhaejo.responseDTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
public class ShowStateResponseDTO {

    @Schema(description = "구역 이름", example = "소음 이상")
    private String zoneName;

    @Schema(description = "구역 이름", example = "냉각기")
    private List<String> machineName;

    @Schema(description = "임계값", example = "85db")
    private int threshold;

    @Schema(description = "상태", example = "0-2회 정상, 3-5회 주의, 6회 이상 비정상")
    private String state; //

    public ShowStateResponseDTO(String zoneName, List<String> machineName, int threshold, String state) {
        this.zoneName = zoneName;
        this.machineName = machineName;
        this.threshold = threshold;
        this.state = state;
    }
}
