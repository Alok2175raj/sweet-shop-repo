package com.sweetshop.dto.sweet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SweetResponseDTO {

    private Long id;
    private String name;
    private String category;
    private double price;
    private int quantity;
}
