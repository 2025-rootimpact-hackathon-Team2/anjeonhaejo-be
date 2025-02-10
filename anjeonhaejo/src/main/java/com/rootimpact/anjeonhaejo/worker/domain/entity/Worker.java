package com.rootimpact.anjeonhaejo.worker.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "worker")
@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Long workerId;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "significant")
    private String significant;

    @Column(name = "condition")
    private String condition;

    @Builder
    private Worker(
            final String name,
            final String department,
            final String significant,
            final String condition
    ) {
        this.name = name;
        this.department = department;
        this.significant = significant;
        this.condition = condition;
    }
}
