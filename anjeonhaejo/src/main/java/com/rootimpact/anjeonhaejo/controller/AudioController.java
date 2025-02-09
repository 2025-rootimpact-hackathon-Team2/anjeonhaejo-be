package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.domain.AudioAnalysis;
import com.rootimpact.anjeonhaejo.responseDTO.EmergencyDecibelResponseDTO;
import com.rootimpact.anjeonhaejo.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/audio")
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @PostMapping("/upload")
    public ResponseEntity<EmergencyDecibelResponseDTO> uploadAudio(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("decibel") double decibel,
                                                     @RequestParam("workerZone") String workerZone) {
        System.out.println(decibel);
        System.out.println(workerZone);
        try {
            EmergencyDecibelResponseDTO result = audioService.analyzeAudio(file, decibel, workerZone);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AudioAnalysis>> getAllAudioFiles() {
        return ResponseEntity.ok(audioService.getAllAudioFiles());
    }

}
