package com.example.Ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.model.CategoryModel;
import com.example.Ecommerce.repository.CategoryRepository;


import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryModel> getAllCategory() {
        log.info("Fetching All Categories!!!");
        // Fetch Categories from DB
        List<Category> categoryList = categoryRepository.findAll();
        // now use stream operator to map with Response
        List<CategoryModel> categoryResponses = categoryList.stream()
                .map(this::convertToCategoryResponse)
                .collect(Collectors.toList());
        log.info("Fetched All Categories!!!");
        return categoryResponses;
    }

    private CategoryModel convertToCategoryResponse(Category category) {
        return CategoryModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
