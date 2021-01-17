package com.example.MStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierDto {

    @NotBlank
    @Size(max = 40)
    private String name;

    private List<ProductSupplyDto> products = new ArrayList<>();
}
