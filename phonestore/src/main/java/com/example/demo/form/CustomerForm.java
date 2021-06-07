package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerForm {
    private String name;
    private String address;
    private String email;
    private String phone;

    private boolean valid;
}
