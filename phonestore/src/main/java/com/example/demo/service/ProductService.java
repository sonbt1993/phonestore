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

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public List<Product> findAll(){
       return productDAO.findAll();
    }

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
        Product product = findProductById(productForm.getId());
        if(product == null){product = new Product();};
        product.setId(productForm.getId());
        product.setName(productForm.getName());
        product.setBrand(productForm.getBrand());
        product.setPrice(productForm.getPrice());

        if (productForm.getFileData() != null) {
            byte[] image = null;
            try {
                image = productForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
                product.setImage(image);
            }
        }
        return productDAO.save(product);
    }

    public Product get(Long id) {
        return productDAO.findById(id).get();
    }

    public void delete(Long id) {
        productDAO.deleteById(id);
    }
}
