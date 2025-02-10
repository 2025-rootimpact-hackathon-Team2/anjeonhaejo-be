package com.rootimpact.anjeonhaejo.initData;

import com.rootimpact.anjeonhaejo.domain.Machine;
import com.rootimpact.anjeonhaejo.domain.User;
import com.rootimpact.anjeonhaejo.domain.WorkerLine;
import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;
import com.rootimpact.anjeonhaejo.repository.MachineRepository;
import com.rootimpact.anjeonhaejo.repository.UserRepository;
import com.rootimpact.anjeonhaejo.repository.WorkerLineRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Datainit {

    private final UserRepository userRepository; // UserRepository 추가
    private final WorkerLineRepository workerLineRepository; // WorkerLineRepository 추가
    private final MachineRepository machineRepository;
    private final PasswordEncoder encoder;

    @PostConstruct
    @Transactional
    public void init() {

        // WorkerLine 데이터 생성
        WorkerLine workerLineA = WorkerLine.builder()
                .zoneName("A")
                .workState("Working")
                .threshold(6)
                .build();

        WorkerLine workerLineB = WorkerLine.builder()
                .zoneName("B")
                .workState("Working")
                .threshold(2)
                .build();

        WorkerLine workerLineC = WorkerLine.builder()
                .zoneName("C")
                .workState("Working")
                .build();

        WorkerLine workerLineD = WorkerLine.builder()
                .zoneName("D")
                .workState("Working")
                .build();

        // WorkerLine 저장
        workerLineRepository.save(workerLineA);
        workerLineRepository.save(workerLineB);
        workerLineRepository.save(workerLineC);
        workerLineRepository.save(workerLineD);

        // User 데이터 생성
        User user1 = new User("작업자1", "Active", encoder.encode("1234"), "user1@example.com", RoleType.WORKER, "Manager1", workerLineA);
        User user2 = new User("작업자2", "Active", encoder.encode("1234"), "user2@example.com", RoleType.WORKER, "Manager2", workerLineB);
        User user3 = new User("작업자3", "Active", encoder.encode("1234"), "user3@example.com", RoleType.WORKER, "Manager3", workerLineC);
        User user4 = new User("작업자4", "Inactive", encoder.encode("1234"), "user4@example.com", RoleType.WORKER, "Manager4", workerLineD);
        User user5 = new User("작업자5", "Active", encoder.encode("1234"), "user5@example.com", RoleType.WORKER, "Manager5", workerLineA);

        // User 저장
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);


//        공장설정: 제철소
//        A구역 원료처리, 고온작업
//        (컨베이어벨트, 용광로)
//
//        B구역 고온제련, 연속적소음과 폭발음 가능
//        (압연기, 크레인)
//
//        C구역 냉각, 부속 장비
//                (펌프, 냉각기)
//
//        D구역 출하/ 물류
//                (지게차 등 운송)
        Machine machine = new Machine("컨베이너 밸트", workerLineA);
        Machine machine1 = new Machine("용광로", workerLineA);
        Machine machine2 = new Machine("크레인", workerLineB);
        Machine machine3 = new Machine("압연기", workerLineB);
        Machine machine4 = new Machine("압연기", workerLineC);
        Machine machine5 = new Machine("냉각기", workerLineC);
        Machine machine6 = new Machine("지게차", workerLineD);
        Machine machine7 = new Machine("운송", workerLineD);

        machineRepository.save(machine);
        machineRepository.save(machine1);
        machineRepository.save(machine2);
        machineRepository.save(machine3);
        machineRepository.save(machine4);
        machineRepository.save(machine5);
        machineRepository.save(machine6);
        machineRepository.save(machine7);



    }
}

