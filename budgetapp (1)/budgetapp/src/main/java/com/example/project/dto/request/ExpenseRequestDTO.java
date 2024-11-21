package com.example.project.dto.request;

import lombok.Data;

@Data
public class ExpenseRequestDTO {
    private String name;
    private Double amount;
    private String date; // Format: yyyy-MM-dd
    private String description;
    private Long categoryId;
}
