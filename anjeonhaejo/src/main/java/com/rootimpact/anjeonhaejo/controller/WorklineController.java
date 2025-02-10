package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.responseDTO.EmergencyDecibelResponseDTO;
import com.rootimpact.anjeonhaejo.responseDTO.ShowStateResponseDTO;
import com.rootimpact.anjeonhaejo.service.WorklineService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workline")
public class WorklineController {

    private final WorklineService worklineService;

    @GetMapping("/show/state")
    private ResponseEntity<List<ShowStateResponseDTO>> showNoiseState() {
        return worklineService.showNoiseState();
    }

}
