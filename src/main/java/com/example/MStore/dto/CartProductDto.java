package com.example.MStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartProductDto extends MessageAbstract {

    private String productId;

    @NotBlank
    @Size(max = 100)
    private String name;

    private float price;

    @NotNull
    @Min(0)
    @Max(10000)
    private int amount;
}
