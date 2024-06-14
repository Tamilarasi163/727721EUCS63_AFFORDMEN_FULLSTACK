package com.example.Ecommerce.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ecommerce.service.ProductService;
import com.example.Ecommerce.service.CompanyService;
import com.example.Ecommerce.model.CategoryModel;
import com.example.Ecommerce.model.CompanyModel;
import com.example.Ecommerce.model.ProductModel;
import com.example.Ecommerce.service.CategoryService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final CompanyService companyService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CompanyService companyService, CategoryService categoryService) {
        this.productService = productService;
        this.companyService = companyService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") Integer productId) {
        ProductModel productResponse = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductModel>> getProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "brandId", required = false) Integer brandId,
            @RequestParam(name = "typeId", required = false) Integer typeId,
            @RequestParam(name = "sort", defaultValue = "name") String sort,
            @RequestParam(name = "order", defaultValue = "asc") String order) {
        // Convert order to Sort direction
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sorting = Sort.by(direction, sort);
        Pageable pageable = PageRequest.of(page, size, sorting);

        Page<ProductModel> productResponses = productService.getProducts(pageable, brandId, typeId, keyword);
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<CompanyModel>> getBrands() {
        List<CompanyModel> brandResponses = companyService.getAllBrands();
        return new ResponseEntity<>(brandResponses, HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<List<CategoryModel>> getTypes() {
        List<CategoryModel> typeResponses = categoryService.getAllTypes();
        return new ResponseEntity<>(typeResponses, HttpStatus.OK);
    }
}
