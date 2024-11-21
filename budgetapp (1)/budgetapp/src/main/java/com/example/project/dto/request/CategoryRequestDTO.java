package com.example.project.dto.request;

import lombok.Data;

@Data
public class CategoryRequestDTO {
    private String name;
    private String type; // "INCOME" or "EXPENSE"
}
