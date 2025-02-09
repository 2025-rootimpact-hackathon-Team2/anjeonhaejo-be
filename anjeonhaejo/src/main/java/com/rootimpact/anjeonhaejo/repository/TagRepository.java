package com.rootimpact.anjeonhaejo.repository;

import com.rootimpact.anjeonhaejo.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
