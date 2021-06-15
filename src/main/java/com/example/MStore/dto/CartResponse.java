package com.example.MStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartResponse extends MessageAbstract {

    private String id;

    private String price;

    private String createdAt;

    private String updatedAt;
}
