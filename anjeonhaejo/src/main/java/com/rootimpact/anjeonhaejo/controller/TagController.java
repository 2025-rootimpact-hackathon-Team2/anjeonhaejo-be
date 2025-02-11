package com.rootimpact.anjeonhaejo.controller;

import com.rootimpact.anjeonhaejo.domain.Tag;
import com.rootimpact.anjeonhaejo.requestDTO.AddTagDTO;
import com.rootimpact.anjeonhaejo.requestDTO.UpdateTagDTO;
import com.rootimpact.anjeonhaejo.responseDTO.ShowAllTageDTO;
import com.rootimpact.anjeonhaejo.responseDTO.TagResponseDTO;
import com.rootimpact.anjeonhaejo.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @Operation(summary = "태그 생성", description = "태그를 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "태그 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PostMapping("/add")
    public ResponseEntity<TagResponseDTO> addTag(@RequestBody AddTagDTO dto) {
        Tag newTag = tagService.addTag(dto.getTagName(), dto.getCategory());
        TagResponseDTO tagResponseDTO = new TagResponseDTO(newTag.getName());
        return ResponseEntity.ok(tagResponseDTO);
    }

    @Operation(summary = "태그 수정", description = "태그를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "태그 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PutMapping("/{tagid}")
    public ResponseEntity<String> updateTag(@RequestBody UpdateTagDTO dto, @PathVariable Long tagid) {
        Tag updatedTag = tagService.updateTag(tagid, dto.getNewTageName(), dto.getNewCategory());
        return ResponseEntity.ok("Good");
    }

    @Operation(summary = "태그 삭제", description = "태그를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "태그 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok("Good");
    }

    @Operation(summary = "태그 조회", description = "전체 태그를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "전체 태그 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @GetMapping
    public ResponseEntity<List<ShowAllTageDTO>> getAllTags() {
        List<ShowAllTageDTO> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }
}