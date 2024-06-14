package com.example.Ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>
{
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
    Specification<Product> searchByNameContaining(String keyword);
    Specification<Product> findByBrandId(Integer brandId);
    Specification<Product> findByTypeId(Integer typeId);
    Specification<Product> findByBrandIdAndTypeId(Integer brandId, Integer typeId);
}
