package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.domain.Noise;
import com.rootimpact.anjeonhaejo.repository.NoiseRepository;
import com.rootimpact.anjeonhaejo.responseDTO.noise.GetMaxDecibelResponse;
import com.rootimpact.anjeonhaejo.responseDTO.noise.GetMinDecibelResponse;
import com.rootimpact.anjeonhaejo.responseDTO.noise.GetMonthAvgDecibelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NoiseService {

    private final NoiseRepository noiseRepository;

    // 몇 월 소음 달라고 하면 그거만 주기
    @Transactional
    public GetMonthAvgDecibelResponse showMonthAvgDecibelResponse(LocalDate localDate) {
        Noise noise = noiseRepository.findByCreatedAt(localDate)
                .orElseThrow();
        return GetMonthAvgDecibelResponse.from(noise);
    }

    @Transactional
    public GetMaxDecibelResponse showMaxDecibelResponse(LocalDate localDate) {
        Noise noise = noiseRepository.findByCreatedAt(localDate)
                .orElseThrow();
        return GetMaxDecibelResponse.from(noise);
    }

    @Transactional
    public GetMinDecibelResponse showMinDecibelResponse(LocalDate localDate) {
        Noise noise = noiseRepository.findByCreatedAt(localDate)
                .orElseThrow();
        return GetMinDecibelResponse.from(noise);
    }
}
