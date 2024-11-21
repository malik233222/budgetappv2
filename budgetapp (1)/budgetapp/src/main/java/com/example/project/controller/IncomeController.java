package com.example.project.controller;

import com.example.project.dto.request.IncomeRequestDTO;
import com.example.project.dto.response.IncomeResponseDTO;
import com.example.project.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public List<IncomeResponseDTO> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @PostMapping
    public IncomeResponseDTO createIncome(@RequestBody IncomeRequestDTO incomeRequestDTO) {
        return incomeService.createIncome(incomeRequestDTO);
    }
}
