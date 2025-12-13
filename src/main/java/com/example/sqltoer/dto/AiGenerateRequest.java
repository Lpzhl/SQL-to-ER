package com.example.sqltoer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiGenerateRequest {
    private String description;
    private String diagramName;
    private String language;
}
