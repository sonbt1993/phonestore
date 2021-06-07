package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailInfo {
    private String id;

    private Long productCode;
    private String productName;

    private int quanity;
    private double price;
    private double amount;
}
