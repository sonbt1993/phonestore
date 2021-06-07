package com.example.demo.model;

import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartInfo {

    private int orderNum;
    private CustomerInfo customerInfo;
    private final List<CartItemInfo> cartItems = new ArrayList<CartItemInfo>();

    private CartItemInfo findItemById(Long id) {
        for (CartItemInfo item : this.cartItems) {
            if (item.getProductInfo().getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public void addProduct(ProductInfo productInfo, int quantity) {
        CartItemInfo item = this.findItemById(productInfo.getId());

        if (item == null) {
            item = new CartItemInfo();
            item.setQuantity(0);
            item.setProductInfo(productInfo);
            this.cartItems.add(item);
        }
        int newQuantity = item.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartItems.remove(item);
        } else {
            item.setQuantity(newQuantity);
        }
    }

    public void updateProduct(Long id, int quantity) {
        CartItemInfo item = this.findItemById(id);

        if (item != null) {
            if (quantity <= 0) {
                this.cartItems.remove(item);
            } else {
                item.setQuantity(quantity);
            }
        }
    }

    public void removeProduct(ProductInfo productInfo) {
        CartItemInfo item = this.findItemById(productInfo.getId());
        if (item != null) {
            this.cartItems.remove(item);
        }
    }

    public boolean isEmpty() {
        return this.cartItems.isEmpty();
    }

    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartItemInfo item : this.cartItems) {
            quantity += item.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartItemInfo item : this.cartItems) {
            total += item.getAmount();
        }
        return total;
    }

    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartItemInfo> items = cartForm.getCartItems();
            for (CartItemInfo item : items) {
                this.updateProduct(item.getProductInfo().getId(), item.getQuantity());
            }
        }

    }

}
