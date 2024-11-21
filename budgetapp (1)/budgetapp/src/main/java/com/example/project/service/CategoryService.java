package com.example.project.service;

import com.example.project.dto.request.CategoryRequestDTO;
import com.example.project.dto.response.CategoryResponseDTO;
import com.example.project.entity.Category;
import com.example.project.entity.CategoryType;
import com.example.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(category ->
                CategoryResponseDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .type(category.getType().toString())
                        .build()
        ).collect(Collectors.toList());
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {
        Category category = Category.builder()
                .name(request.getName())
                .type(CategoryType.valueOf(request.getType()))
                .build();

        Category savedCategory = categoryRepository.save(category);

        return CategoryResponseDTO.builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .type(savedCategory.getType().toString())
                .build();
    }
}
