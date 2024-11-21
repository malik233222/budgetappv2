package com.example.project.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomeResponseDTO {
    private Long id;
    private Double amount;
    private String date;
    private String description;
    private String categoryName;
}
