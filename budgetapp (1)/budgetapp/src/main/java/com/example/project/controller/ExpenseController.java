package com.example.project.controller;

import com.example.project.dto.request.ExpenseRequestDTO;
import com.example.project.dto.response.ExpenseResponseDTO;
import com.example.project.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public ExpenseResponseDTO createExpense(@RequestBody ExpenseRequestDTO expenseRequestDTO) {
        return expenseService.createExpense(expenseRequestDTO);
    }
}
