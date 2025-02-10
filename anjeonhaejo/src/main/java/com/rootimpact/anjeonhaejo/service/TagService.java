package com.rootimpact.anjeonhaejo.service;

import com.rootimpact.anjeonhaejo.domain.Machine;
import com.rootimpact.anjeonhaejo.domain.Tag;
import com.rootimpact.anjeonhaejo.repository.TagRepository;
import com.rootimpact.anjeonhaejo.responseDTO.ShowAllTageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    public Tag addTag(String name, String category) {
        Tag tag = new Tag(name, category);
        return tagRepository.save(tag);
    }

    public Tag updateTag(Long tagid, String newName, String newCategory) {
        Tag tag = tagRepository.findById(tagid)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다: " + tagid));
        tag.updateName(newName); // 엔티티에 update 메서드 추가 필요
        tag.updateName(newCategory);
        return tag;
    }

    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 태그가 존재하지 않습니다: " + id);
        }
        tagRepository.deleteById(id);
    }

    // 카테고리별 태그 그룹화
    public List<ShowAllTageDTO> getAllTags() {
        // 전체 태그를 카테고리별로 그룹화
        Map<String, List<Tag>> groupedByCategory = tagRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Tag::getCategory));

        // 그룹화된 결과를 DTO 리스트로 변환
        return groupedByCategory.entrySet()
                .stream()
                .map(entry -> {
                    String category = entry.getKey();
                    // 해당 카테고리에 속하는 태그들의 이름을 추출
                    List<String> tagNames = entry.getValue()
                            .stream()
                            .map(Tag::getName)
                            .collect(Collectors.toList());
                    return ShowAllTageDTO.builder()
                            .tagName(tagNames)
                            .categoryName(category)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
