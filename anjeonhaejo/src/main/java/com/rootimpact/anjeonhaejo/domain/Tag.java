package com.rootimpact.anjeonhaejo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    private String category;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReportTagMap> reportMap = new HashSet<>();

    public void updateName(String newName) {
        this.name = newName;
    }

    public void updateCategory(String newCategory) {
        this.category = newCategory;
    }

    @Builder
    public Tag(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
