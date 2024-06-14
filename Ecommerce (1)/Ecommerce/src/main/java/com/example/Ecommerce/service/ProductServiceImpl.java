package com.example.Ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.model.ProductModel;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.exception.ProductNotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductModel getProductById(Integer productId) {
        log.info("Fetching Product by Id: {}", productId);
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException("Product doesn't exist"));
        // Convert the Product to ProductModel
        ProductModel productModel = convertToProductModel(product);
        log.info("Fetched Product by Product Id: {}", productId);
        return productModel;
    }

    @Override
    public Page<ProductModel> getProducts(Pageable pageable, Integer companyId, Integer categoryId, String keyword) {
        Specification<Product> spec = Specification.where(null);

        if (companyId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("company").get("id"), companyId));
        }

        if (categoryId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), categoryId));
        }

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("productName"), "%" + keyword + "%"));
        }

        return productRepository.findAll(spec, pageable).map(this::convertToProductModel);
    }

    private ProductModel convertToProductModel(Product product) {
        String companyName = (product.getCompany() != null) ? product.getCompany().getName() : "Unknown Company";
        String categoryName = (product.getCategory() != null) ? product.getCategory().getName() : "Unknown Category";
        
        return ProductModel.builder()
            .id(product.getId())
            .productName(product.getProductName())
            .price(product.getPrice())
            .rating(product.getRating())
            .discount(product.getDiscount())
            .availability(product.getAvailability())
            .companyName(companyName)
            .categoryName(categoryName)
            .build();
    }
}
