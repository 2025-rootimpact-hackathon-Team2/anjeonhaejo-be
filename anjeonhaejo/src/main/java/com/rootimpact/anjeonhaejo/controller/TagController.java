package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.domain.Tag;
import com.rootimpact.anjeonhaejo.requestDTO.AddTagDTO;
import com.rootimpact.anjeonhaejo.requestDTO.UpdateTagDTO;
import com.rootimpact.anjeonhaejo.responseDTO.ShowAllTageDTO;
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

    @GetMapping("/add")
    public ResponseEntity<TagResponseDTO> addTag(@RequestBody AddTagDTO dto) {
        Tag newTag = tagService.addTag(dto.getTagName(), dto.getCategory());
        TagResponseDTO tagResponseDTO = new TagResponseDTO(newTag.getName());
        return ResponseEntity.ok(tagResponseDTO);
    }

    @PutMapping("/{tagid}")
    public ResponseEntity<String> updateTag(@RequestBody UpdateTagDTO dto, @PathVariable Long tagid) {
        Tag updatedTag = tagService.updateTag(tagid, dto.getNewTageName(), dto.getNewCategory());
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok("Good");
    }

    @GetMapping
    public ResponseEntity<List<ShowAllTageDTO>> getAllTags() {
        List<ShowAllTageDTO> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }
}