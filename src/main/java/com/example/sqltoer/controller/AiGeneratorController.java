package com.example.sqltoer.controller;

import com.example.sqltoer.dto.AiGenerateRequest;
import com.example.sqltoer.dto.ApiResponse;
import com.example.sqltoer.dto.ErDiagramResponse;
import com.example.sqltoer.model.ERDiagram;
import com.example.sqltoer.service.AiGeneratorService;
import com.example.sqltoer.service.ErDiagramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AiGeneratorController {

    private final AiGeneratorService aiGeneratorService;
    private final ErDiagramService erDiagramService;

    @PostMapping("/generate")
    public ApiResponse<ErDiagramResponse> generateFromDescription(@RequestBody AiGenerateRequest request) {
        try {
            log.info("Received AI generation request for diagram: {}", request.getDiagramName());

            if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
                return ApiResponse.error(400, "Description cannot be empty");
            }

            ERDiagram diagram = aiGeneratorService.generateFromDescription(
                    request.getDescription(),
                    request.getDiagramName()
            );

            String graphData = erDiagramService.generateGraphData(diagram);

            ErDiagramResponse response = ErDiagramResponse.builder()
                    .diagram(diagram)
                    .graphData(graphData)
                    .tableCount(diagram.getTables().size())
                    .relationshipCount(diagram.getRelationships().size())
                    .build();

            return ApiResponse.success(response);

        } catch (Exception e) {
            log.error("Error generating ER diagram from AI", e);
            return ApiResponse.error("Failed to generate diagram: " + e.getMessage());
        }
    }

    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.success("AI Generator service is running");
    }
}
