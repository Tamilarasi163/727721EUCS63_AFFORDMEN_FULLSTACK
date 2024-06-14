package com.example.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Ecommerce.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>
{

}
