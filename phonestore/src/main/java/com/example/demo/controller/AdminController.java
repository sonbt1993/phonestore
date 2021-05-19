package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.form.ProductForm;
import com.example.demo.service.ProductService;
import com.example.demo.validator.ProductFormValidator;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @Autowired
    ProductService productService;

    @Autowired
    private ProductFormValidator productFormValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object o = dataBinder.getTarget();
        if (o == null) {
            return;
        }
        System.out.println("Target=" + o);

        if (o.getClass() == ProductForm.class) {
            dataBinder.setValidator(productFormValidator);
        }
    }

    @GetMapping("/admin/login")
    public String login(Model model) {

        return "login";
    }

    @GetMapping("/admin/accountInfo")
    public String accountInfo(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());

        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }

    @GetMapping("/admin/editProduct")
    public String product(Model model, @RequestParam(value = "id", defaultValue = "")  Long id) {
        ProductForm productForm = new ProductForm();
        Product product = productService.findProductById(id);
        productForm.setId(product.getId());
        productForm.setName(product.getName());
        productForm.setPrice(product.getPrice());
        model.addAttribute("productForm", productForm);
        System.out.println(product);
        System.out.println(productForm);
        return "product";
    }

    @PostMapping("/admin/editProduct")
    public String saveProduct(Model model,
                              @ModelAttribute("productForm") @Validated ProductForm productForm,
                              BindingResult result) {

        if (result.hasErrors()) {
            return "product";
        }
        try {
            productService.saveProductForm(productForm);
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            String message = rootCause.getMessage();
            model.addAttribute("errorMessage", message);
            return "product";
        }
        return "redirect:/productList";
    }

    @GetMapping("/admin/createProduct")
    public String createProduct(Model model) {
        ProductForm productForm = new ProductForm();
        model.addAttribute("productForm", productForm);
        return "createProduct";
    }

    @PostMapping("/admin/createProduct")
    public String createProduct(Model model,
                              @ModelAttribute("productForm") ProductForm productForm,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "createProduct";
        }
        try {
        productService.saveProductForm(productForm);
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            String message = rootCause.getMessage();
            model.addAttribute("errorMessage", message);
            return "createProduct";
        }
        return "redirect:/productList";
    }

    @GetMapping("/admin/deleteProduct")
    public String deleteProduct( @RequestParam("id") Long id) {
        productService.delete(id);
        return "redirect:/productList";
    }


}
