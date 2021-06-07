package com.example.demo.model;

import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemInfo {

    private int quantity;
    private ProductInfo productInfo;

    public double getAmount() {
        return this.productInfo.getPrice() * this.quantity;
    }

}
