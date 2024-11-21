package com.example.project.service;

import com.example.project.dto.request.ExpenseRequestDTO;
import com.example.project.dto.response.ExpenseResponseDTO;
import com.example.project.entity.Category;
import com.example.project.entity.Expense;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream().map(expense ->
                ExpenseResponseDTO.builder()
                        .id(expense.getId())
                        .amount(expense.getAmount())
                        .date(expense.getDate().toString())
                        .description(expense.getDescription())
                        .categoryName(expense.getCategory().getName())
                        .build()
        ).collect(Collectors.toList());
    }

    public ExpenseResponseDTO createExpense(ExpenseRequestDTO request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = Expense.builder()
                .amount(request.getAmount())
                .date(java.time.LocalDate.parse(request.getDate()))
                .description(request.getDescription())
                .category(category)
                .build();

        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseResponseDTO.builder()
                .id(savedExpense.getId())
                .amount(savedExpense.getAmount())
                .date(savedExpense.getDate().toString())
                .description(savedExpense.getDescription())
                .categoryName(savedExpense.getCategory().getName())
                .build();
    }
}
