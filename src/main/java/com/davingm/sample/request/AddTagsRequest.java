package com.davingm.sample.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddTagsRequest {
    @NotEmpty(message = "Tags ID tidak boleh kosong")
    @JsonProperty("tags_id")
    private List<Long> tagsId;
}
