package com.example.MStore.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartDto {

    private String id;

    private List<CartProductDto> cartProducts = new ArrayList<>();

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("100000.0")
    private float price;
}
