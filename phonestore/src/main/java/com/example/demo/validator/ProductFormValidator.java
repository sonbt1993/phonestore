package com.example.demo.validator;

import com.example.demo.entity.Product;
import com.example.demo.form.ProductForm;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductFormValidator implements Validator {
    @Autowired
    ProductService productService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == ProductForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductForm productForm = (ProductForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brand", "NotEmpty.productForm.brand");
    }
}
