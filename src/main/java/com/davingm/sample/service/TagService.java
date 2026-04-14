package com.davingm.sample.service;

// Service tag

import java.util.List;

import org.springframework.stereotype.Service;

import com.davingm.sample.model.Tag;
import com.davingm.sample.repository.TagRepository;
import com.davingm.sample.request.TagCreate;
import com.davingm.sample.request.TagUpdate;

@Service
public class TagService {
    
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(TagCreate request) {
        Tag tag = new Tag();
        tag.setName(request.getName());
        return tagRepository.save(tag);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public java.util.Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    public Tag updateTag(Long id, TagUpdate request) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag tidak ditemukan"));
        tag.setName(request.getName());
        return tagRepository.save(tag);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
