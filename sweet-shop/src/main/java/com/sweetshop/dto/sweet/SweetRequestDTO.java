package com.sweetshop.dto.sweet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SweetRequestDTO {

    private String name;
    private String category;
    private double price;
    private int quantity;
}
