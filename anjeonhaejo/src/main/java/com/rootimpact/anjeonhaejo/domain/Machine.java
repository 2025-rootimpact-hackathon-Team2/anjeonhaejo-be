package com.rootimpact.anjeonhaejo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Machine {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "machine_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "worker_line_id")
    private WorkerLine workerLine;

    public Machine(String name, WorkerLine workerLine) {
        this.name = name;
        this.workerLine = workerLine;
    }
}
