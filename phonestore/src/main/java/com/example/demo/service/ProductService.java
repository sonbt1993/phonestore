package com.example.demo.service;

import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Product;
import com.example.demo.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public Page<Product> listAll(int pageNum, String sortField, String sortDir) {

        Pageable pageable = PageRequest.of(pageNum - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        return productDAO.findAll(pageable);
    }

    public Product findProductById(Long id){
        return productDAO.findProductById(id);
    };

    public void save(Product product) {
        productDAO.save(product);
    }

    public Product saveProductForm(ProductForm productForm) {
        Product product = null;
        product.setId(productForm.getId());
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        return productDAO.save(product);
    }

    public Product get(Long id) {
        return productDAO.findById(id).get();
    }

    public void delete(Long id) {
        productDAO.deleteById(id);
    }
}
