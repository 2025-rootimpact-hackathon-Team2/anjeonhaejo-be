package com.rootimpact.anjeonhaejo.responseDTO;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
public class ShowStateResponseDTO {

    private String zoneName;

    private List<String> machineName;

    private int threshold;

    private String state; // 0-2회 정상, 3-5회 주의, 6회 이상 비정상

    public ShowStateResponseDTO(String zoneName, List<String> machineName, int threshold, String state) {
        this.zoneName = zoneName;
        this.machineName = machineName;
        this.threshold = threshold;
        this.state = state;
    }
}
