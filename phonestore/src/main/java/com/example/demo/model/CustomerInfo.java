package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo {
    private String name;
    private String address;
    private String email;
    private String phone;

    private boolean valid;
}
