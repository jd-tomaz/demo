package com.example.demo.rest;

import com.example.demo.services.TagService;
import com.example.demo.to.TagDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: query param, get tag by substring
@RestController
@RequestMapping("/api/tags")
public class TagResource {

    private final TagService tagService;

    public TagResource(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDto> getTags(@RequestParam(required = false) String tagName) {
        if (tagName == null) {
            return tagService.getTags();
        }

        return tagService.getTags(tagName);
    }

    @PostMapping
    public TagDto createTag(@RequestBody TagDto tag) {
        return tagService.saveTag(tag);
    }

    @DeleteMapping("{tagId}")
    public void deleteTag(@PathVariable String tagId) {
        tagService.deleteTag(tagId);
    }

}
