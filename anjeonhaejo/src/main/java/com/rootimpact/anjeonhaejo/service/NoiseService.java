package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.domain.Noise;
import com.rootimpact.anjeonhaejo.repository.NoiseRepository;
import com.rootimpact.anjeonhaejo.responseDTO.noise.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoiseService {

    private final double MAX_MIN_AVERAGE_NOISE_DIVIDE_NUM = 2;
    private final NoiseRepository noiseRepository;

    // 월간 평균 데시벨 --> 해당 연월의 전체 최고, 최저의 평균. 즉, 값이 딱 하나만 떨어짐
    @Transactional
    public ReadMonthAvgDecibelResponse showMonthAvgDecibelResponse(LocalDate localDate) {
        int year = localDate.getYear();
        int month = localDate.getMonthValue();

        List<Noise> noises = noiseRepository.findByYearAndMonth(year, month);

        if (noises.isEmpty()) {
            throw new IllegalStateException("해당 월에 대한 소음 데이터가 존재하지 않습니다.");
        }

        double avgMaxDecibel = noises.stream()
                .mapToDouble(Noise::getMaxDecibel)
                .average()
                .orElse(0.0);
        double avgMinDecibel = noises.stream()
                .mapToDouble(Noise::getMinDecibel)
                .average()
                .orElse(0.0);

        double monthAvgDecibel = Math.round(((avgMaxDecibel + avgMinDecibel) / MAX_MIN_AVERAGE_NOISE_DIVIDE_NUM) * 10.0) / 10.0;

        return new ReadMonthAvgDecibelResponse(monthAvgDecibel);
    }

    @Transactional
    public ReadMaxDecibelResponse showMaxDecibelResponse(LocalDate localDate) {
        Noise noise = noiseRepository.findByCreatedAt(localDate)
                .orElseThrow();
        return ReadMaxDecibelResponse.from(noise);
    }

    @Transactional
    public ReadMinDecibelResponse showMinDecibelResponse(LocalDate localDate) {
        Noise noise = noiseRepository.findByCreatedAt(localDate)
                .orElseThrow();
        return ReadMinDecibelResponse.from(noise);
    }

    @Transactional
    public ReadDailyAveragePerMonthDecibelResponse showDayPerMonthAvgDecibelResponse(LocalDate localDate) {
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        List<ReadDayAvgDecibelAResponse> dailyAverageDecibelAs = new ArrayList<>();
        List<ReadDayAvgDecibelBResponse> dailyAverageDecibelBs = new ArrayList<>();
        List<ReadDayAvgDecibelCResponse> dailyAverageDecibelCs = new ArrayList<>();
        List<ReadDayAvgDecibelDResponse> dailyAverageDecibelDs = new ArrayList<>();

        for (int d = 1; d <= day; d++) {
            LocalDate currentDate = LocalDate.of(year, month, d);
            List<Noise> noises = noiseRepository.findAllByCreatedAt(currentDate);

            Map<String, List<Noise>> noisesByZone = noises.stream()
                    .collect(Collectors.groupingBy(Noise::getZone));

            noisesByZone.forEach((zone, noiseList) -> {
                if (!noiseList.isEmpty()) {
                    double avrMaxDecibel = noiseList.stream()
                            .mapToDouble(Noise::getMaxDecibel)
                            .average()
                            .orElse(0.0);
                    double avrMinDecibel = noiseList.stream()
                            .mapToDouble(Noise::getMinDecibel)
                            .average()
                            .orElse(0.0);

                    double avrDailyDecibel = Math.round(((avrMaxDecibel + avrMinDecibel) / MAX_MIN_AVERAGE_NOISE_DIVIDE_NUM) * 10.0) / 10.0;

                    switch (zone) {
                        case "A" -> dailyAverageDecibelAs.add(ReadDayAvgDecibelAResponse.of(currentDate, avrDailyDecibel, zone));
                        case "B" -> dailyAverageDecibelBs.add(ReadDayAvgDecibelBResponse.of(currentDate, avrDailyDecibel, zone));
                        case "C" -> dailyAverageDecibelCs.add(ReadDayAvgDecibelCResponse.of(currentDate, avrDailyDecibel, zone));
                        case "D" -> dailyAverageDecibelDs.add(ReadDayAvgDecibelDResponse.of(currentDate, avrDailyDecibel, zone));
                    }
                }
            });
        }

        return ReadDailyAveragePerMonthDecibelResponse.of(
                dailyAverageDecibelAs,
                dailyAverageDecibelBs,
                dailyAverageDecibelCs,
                dailyAverageDecibelDs
        );
    }

//    // 월별 소음 트렌드 --> 해당 연월의 전체 최고, 최저별로 각 일자별 하나의 평균이 전부 떨어짐
//    @Transactional
//    public ReadDailyAveragePerMonthDecibelResponse showDayPerMonthAvgDecibelResponse(LocalDate localDate) {
//        int year = localDate.getYear();
//        int month = localDate.getMonthValue();
//        int day = localDate.getDayOfMonth();
//
//        List<ReadDayAvgDecibelResponse> dayAvgDecibels = new ArrayList<>();
//
//        for (int d = 1; d <= day; d++) {
//            LocalDate currentDate = LocalDate.of(year, month, d);
//
//            List<Noise> noises = noiseRepository.findByCreatedAt(currentDate).stream()
//                    .toList();
//
//            if (!noises.isEmpty()) {
//                double avrMaxDecibel = noises.stream()
//                        .mapToDouble(Noise::getMaxDecibel)
//                        .average()
//                        .orElse(0.0);
//                double avrMinDecibel = noises.stream()
//                        .mapToDouble(Noise::getMinDecibel)
//                        .average()
//                        .orElse(0.0);
//
//                double avrDailyDecibel = Math.round(((avrMaxDecibel + avrMinDecibel) / MAX_MIN_AVERAGE_NOISE_DIVIDE_NUM) * 10.0) / 10.0;
//
//                dayAvgDecibels.add(ReadDayAvgDecibelResponse.of(currentDate, avrDailyDecibel));
//            }
//        }
//
//        return ReadDailyAveragePerMonthDecibelResponse.from(dayAvgDecibels);
//    }
}
