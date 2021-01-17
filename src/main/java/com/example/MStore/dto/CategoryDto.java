package com.example.MStore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private String id;

    @NotBlank
    @Size(max = 100)
    private String name;

    private Set<ProductDto> products = new HashSet<>();
}
