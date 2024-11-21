package com.example.project.dto.request;

import lombok.Data;

@Data
public class ExpenseRequestDTO {
    private Double amount;
    private String date; // Format: YYYY-MM-DD
    private String description;
    private Long categoryId;
}
