package com.example.project.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class ExpenseResponseDTO {
    private Long id;
    private Double amount;
    private String date;   // Tarih
    private String description; // Açıklama
    private String categoryName; // Kategori adı

    public ExpenseResponseDTO(Long id, String description, Double amount, String date, String categoryName) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.categoryName = categoryName;
    }
}
