package com.example.project.dto.request;

import lombok.Data;

@Data
public class IncomeRequestDTO {
    private Double amount;
    private String date; // YYYY-MM-DD
    private String description;
    private Long categoryId;
}
