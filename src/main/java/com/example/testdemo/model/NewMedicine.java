package com.example.testdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewMedicine {

    private String name;
    private double price;
    private long amount;
    private String grammage;
}
