package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ProductService productService;

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
}
