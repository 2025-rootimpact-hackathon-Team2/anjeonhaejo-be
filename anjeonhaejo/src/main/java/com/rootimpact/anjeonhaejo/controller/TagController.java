package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.domain.Tag;
import com.rootimpact.anjeonhaejo.responseDTO.TagResponseDTO;
import com.rootimpact.anjeonhaejo.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/add/{name}")
    public ResponseEntity<TagResponseDTO> addTag(@PathVariable("name") String name) {
        Tag newTag = tagService.addTag(name);
        TagResponseDTO tagResponseDTO = new TagResponseDTO(newTag.getName());
        return ResponseEntity.ok(tagResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestParam String newName) {
        Tag updatedTag = tagService.updateTag(id, newName);
        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok("태그가 삭제되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }
}