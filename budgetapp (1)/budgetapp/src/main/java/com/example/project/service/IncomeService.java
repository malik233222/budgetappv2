package com.example.project.service;

import com.example.project.dto.request.IncomeRequestDTO;
import com.example.project.dto.response.IncomeResponseDTO;
import com.example.project.entity.Category;
import com.example.project.entity.Income;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<IncomeResponseDTO> getAllIncomes() {
        return incomeRepository.findAll().stream().map(income ->
                IncomeResponseDTO.builder()
                        .id(income.getId())
                        .amount(income.getAmount())
                        .date(income.getDate().toString())
                        .description(income.getDescription())
                        .categoryName(income.getCategory().getName())
                        .build()
        ).collect(Collectors.toList());
    }

    public IncomeResponseDTO createIncome(IncomeRequestDTO request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Income income = Income.builder()
                .amount(request.getAmount())
                .date(java.time.LocalDate.parse(request.getDate()))
                .description(request.getDescription())
                .category(category)
                .build();

        Income savedIncome = incomeRepository.save(income);

        return IncomeResponseDTO.builder()
                .id(savedIncome.getId())
                .amount(savedIncome.getAmount())
                .date(savedIncome.getDate().toString())
                .description(savedIncome.getDescription())
                .categoryName(savedIncome.getCategory().getName())
                .build();
    }
}
