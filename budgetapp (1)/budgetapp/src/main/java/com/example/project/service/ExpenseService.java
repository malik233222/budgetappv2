package com.example.project.service;

import com.example.project.dto.request.ExpenseRequestDTO;
import com.example.project.dto.response.ExpenseResponseDTO;
import com.example.project.entity.Category;
import com.example.project.entity.Expense;
import com.example.project.entity.User;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ExpenseRepository;
import com.example.project.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public ExpenseResponseDTO createExpense(ExpenseRequestDTO requestDTO) {
        if (requestDTO.getCategoryId() == null) {
            throw new RuntimeException("Category ID is required");
        }

        // Kullanıcı kimliğini al
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kategori doğrulaması
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Expense oluştur
        Expense expense = new Expense();
        expense.setDescription(requestDTO.getName());
        expense.setAmount(requestDTO.getAmount());
        expense.setDate(requestDTO.getDate() != null
                ? LocalDate.parse(requestDTO.getDate())
                : LocalDate.now());
        expense.setCategory(category);
        expense.setUser(user); // User bilgisi ekleniyor

        return mapToResponseDTO(expenseRepository.save(expense));
    }

    private ExpenseResponseDTO mapToResponseDTO(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate() != null ? expense.getDate().toString() : null,
                expense.getCategory() != null ? expense.getCategory().getName() : null
        );
    }
}
