package com.rootimpact.anjeonhaejo.initData;

import com.rootimpact.anjeonhaejo.domain.User;
import com.rootimpact.anjeonhaejo.domain.WorkerLine;
import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;
import com.rootimpact.anjeonhaejo.repository.UserRepository;
import com.rootimpact.anjeonhaejo.repository.WorkerLineRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Datainit {

    private final UserRepository userRepository; // UserRepository 추가
    private final WorkerLineRepository workerLineRepository; // WorkerLineRepository 추가

    @PostConstruct
    @Transactional
    public void init() {

        // WorkerLine 데이터 생성
        WorkerLine workerLineA = WorkerLine.builder()
                .zoneName("A")
                .workState("Working")
                .temperature(22L)
                .oxygenSaturation(12L)
                .co2Level(3L)
                .flammableGasLevel(12L)
                .noiseLevel(23L)
                .vibrationLevel(43L)
                .build();

        WorkerLine workerLineB = WorkerLine.builder()
                .zoneName("B")
                .workState("Working")
                .temperature(25L)
                .oxygenSaturation(22L)
                .co2Level(33L)
                .flammableGasLevel(15L)
                .noiseLevel(13L)
                .vibrationLevel(43L)
                .build();

        WorkerLine workerLineC = WorkerLine.builder()
                .zoneName("C")
                .workState("Working")
                .temperature(22L)
                .oxygenSaturation(12L)
                .co2Level(3L)
                .flammableGasLevel(12L)
                .noiseLevel(23L)
                .vibrationLevel(43L)
                .build();

        WorkerLine workerLineD = WorkerLine.builder()
                .zoneName("D")
                .workState("Working")
                .temperature(22L)
                .oxygenSaturation(12L)
                .co2Level(3L)
                .flammableGasLevel(12L)
                .noiseLevel(23L)
                .vibrationLevel(43L)
                .build();

        // WorkerLine 저장
        workerLineRepository.save(workerLineA);
        workerLineRepository.save(workerLineB);
        workerLineRepository.save(workerLineC);
        workerLineRepository.save(workerLineD);

        // User 데이터 생성
        User user1 = new User("작업자1", "Active", "password1", "user1@example.com", RoleType.WORKER, "Manager1", workerLineA);
        User user2 = new User("작업자2", "Active", "password2", "user2@example.com", RoleType.WORKER, "Manager2", workerLineB);
        User user3 = new User("작업자3", "Active", "password3", "user3@example.com", RoleType.WORKER, "Manager3", workerLineC);
        User user4 = new User("작업자4", "Inactive", "password4", "user4@example.com", RoleType.WORKER, "Manager4", workerLineD);
        User user5 = new User("작업자5", "Active", "password5", "user5@example.com", RoleType.WORKER, "Manager5", workerLineA);

        // User 저장
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
    }
}

