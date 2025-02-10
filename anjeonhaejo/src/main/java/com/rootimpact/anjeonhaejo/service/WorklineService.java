package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.controller.WorklineController;
import com.rootimpact.anjeonhaejo.domain.Machine;
import com.rootimpact.anjeonhaejo.domain.WorkerLine;
import com.rootimpact.anjeonhaejo.repository.WorkerLineRepository;
import com.rootimpact.anjeonhaejo.responseDTO.ShowStateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorklineService {

    private final WorkerLineRepository workerLineRepository;


    public ResponseEntity<List<ShowStateResponseDTO>> showNoiseState() {
        List<ShowStateResponseDTO> responseList = workerLineRepository.findAll()
                .stream()
                .map(workerLine -> {
                    // 해당 workerLine에 속한 machine들의 이름 가져오기
                    List<String> machineNames = workerLine.getMachines()
                            .stream()
                            .map(Machine::getName)
                            .collect(Collectors.toList());

                    // threshold 값에 따라 상태 결정
                    String state;
                    if (workerLine.getThreshold() <= 2) {
                        state = "정상";
                    } else if (workerLine.getThreshold() <= 5) {
                        state = "주의";
                    } else {
                        state = "비정상";
                    }

                    return new ShowStateResponseDTO(
                            workerLine.getZoneName(),
                            machineNames, // machineName 목록 추가
                            workerLine.getThreshold(),
                            state
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
}
