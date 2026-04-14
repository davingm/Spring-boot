package com.davingm.sample.controller;

// Controller tag membuat CRUD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.davingm.sample.model.Tag;
import com.davingm.sample.request.TagCreate;
import com.davingm.sample.request.TagUpdate;
import com.davingm.sample.response.WebResponse;
import com.davingm.sample.service.TagService;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // CREATE
    @PostMapping
    public WebResponse<Tag> createTag(@Valid @RequestBody TagCreate request) {
        return WebResponse.<Tag>builder()
                .status("00")
                .pesan("Tag berhasil ditambahkan")
                .data(tagService.createTag(request))
                .build();
    }

    // READ ALL
    @GetMapping
    public WebResponse<List<Tag>> getAllTags() {
        return WebResponse.<List<Tag>>builder()
                .status("00")
                .pesan("Berhasil mengambil semua data tag")
                .data(tagService.getAllTags())
                .build();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public WebResponse<Tag> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id)
                .map(tag -> WebResponse.<Tag>builder()
                        .status("00")
                        .pesan("Berhasil mengambil data tag")
                        .data(tag)
                        .build())
                .orElseThrow(() -> new RuntimeException("Tag tidak ditemukan"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public WebResponse<Tag> updateTag(@PathVariable Long id, @Valid @RequestBody TagUpdate request) {
        return WebResponse.<Tag>builder()
                .status("00")
                .pesan("Tag berhasil diupdate")
                .data(tagService.updateTag(id, request))
                .build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public WebResponse<String> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return WebResponse.<String>builder()
                .status("00")
                .pesan("Tag dengan ID " + id + " berhasil dihapus")
                .data(null)
                .build();
    }
}
