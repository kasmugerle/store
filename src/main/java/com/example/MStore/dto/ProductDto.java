package com.example.MStore.dto;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private String id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("100000.0")
    private float price;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100000.0")
    private float ddv;
}
