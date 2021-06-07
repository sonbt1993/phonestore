package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.form.CustomerForm;
import com.example.demo.form.ProductForm;
import com.example.demo.model.CartInfo;
import com.example.demo.model.ProductInfo;
import com.example.demo.service.ProductService;
import com.example.demo.ultils.Utils;
import com.example.demo.validator.CustomerFormValidator;
import com.example.demo.validator.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Transactional
@Controller
public class MainController {

    @Autowired
    ProductService productService;

    @Autowired
    private ProductFormValidator productFormValidator;

    @Autowired
    private CustomerFormValidator customerFormValidator;


    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == ProductForm.class) {
            dataBinder.setValidator(productFormValidator);
        }

        else if (target.getClass() == CustomerForm.class) {
            dataBinder.setValidator(customerFormValidator);
        }
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/productList/1?sortField=id&sortDir=asc";
    }

    @GetMapping("/productList")
    public String listProduct(Model model)
    {
        return "redirect:/productList/1?sortField=id&sortDir=asc";
    }

    @GetMapping("/productList/{pageNum}")
    public String listProductHandler(Model model, @PathVariable(name = "pageNum") int pageNum,
                                     @RequestParam("sortField") String sortField,
                                     @RequestParam("sortDir") String sortDir){

        Page<Product> page = productService.listAll(pageNum, sortField, sortDir);

        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @GetMapping("/productImage" )
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam("id") Long id) throws IOException {
        Product product = null;

        if (id != null) {
            product = this.productService.findProductById(id);
        }

        if (product != null && product.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(product.getImage());
        }

        response.getOutputStream().close();
    }

    @GetMapping("/shoppingCart")
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);

        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }

    @GetMapping( "/buyProduct" )
    public String listProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") Long id) {

        Product product = null;
        if (id != null) {
            product = productService.findProductById(id);
        }

        if (product != null) {
            CartInfo cartInfo = Utils.getCartInSession(request);

            ProductInfo productInfo = new ProductInfo(product);

            cartInfo.addProduct(productInfo, 1);
        }

        return "redirect:/shoppingCart";
    }

    @GetMapping( "/shoppingCartRemoveProduct" )
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "id", defaultValue = "") Long id) {
        Product product = null;
        if (id != null) {
            product = productService.findProductById(id);
        }

        if (product != null) {
            CartInfo cartInfo = Utils.getCartInSession(request);

            ProductInfo productInfo = new ProductInfo(product);

            cartInfo.removeProduct(productInfo);
        }

        return "redirect:/shoppingCart";
    }


    @PostMapping("/shoppingCart" )
    public String shoppingCartUpdateQty(HttpServletRequest request, //
                                        Model model, //
                                        @ModelAttribute("cartForm") CartInfo cartForm) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        return "redirect:/shoppingCart";
    }




}
