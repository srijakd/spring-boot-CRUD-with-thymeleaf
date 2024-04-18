package com.spring.restapitutorial.controller;

import com.spring.restapitutorial.entity.Product;
import com.spring.restapitutorial.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getAllProducts(Model model) {
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("products", productList);
        return "list-products";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add-edit-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.createProduct(product);
        return "redirect:/";
    }

    @GetMapping("/product-update/{id}")
    public String getProduct(Model model, @PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "add-edit-product";
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
