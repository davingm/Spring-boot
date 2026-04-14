package com.davingm.sample.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagCreate {
    @NotBlank(message = "Nama tag wajib diisi")
    private String name;
}
