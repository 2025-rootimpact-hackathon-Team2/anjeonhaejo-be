package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.domain.Tag;
import com.rootimpact.anjeonhaejo.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    // ✅ 태그 추가
    public Tag addTag(String name) {
        Tag tag = new Tag(name);
        return tagRepository.save(tag);
    }

    // ✅ 태그 수정
    public Tag updateTag(Long id, String newName) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다: " + id));
        tag.updateName(newName); // 엔티티에 update 메서드 추가 필요
        return tag;
    }

    // ✅ 태그 삭제
    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 태그가 존재하지 않습니다: " + id);
        }
        tagRepository.deleteById(id);
    }

    // ✅ 모든 태그 조회
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
