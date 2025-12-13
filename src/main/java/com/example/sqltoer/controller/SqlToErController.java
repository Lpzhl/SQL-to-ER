package com.example.sqltoer.controller;

import com.example.sqltoer.dto.ApiResponse;
import com.example.sqltoer.dto.ErDiagramResponse;
import com.example.sqltoer.dto.SqlToErRequest;
import com.example.sqltoer.model.ERDiagram;
import com.example.sqltoer.service.ErDiagramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/sql")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SqlToErController {

    private final ErDiagramService erDiagramService;

    @PostMapping("/parse")
    public ApiResponse<ErDiagramResponse> parseSql(@RequestBody SqlToErRequest request) {
        try {
            log.info("Received SQL parse request for diagram: {}", request.getDiagramName());

            if (request.getSql() == null || request.getSql().trim().isEmpty()) {
                return ApiResponse.error(400, "SQL cannot be empty");
            }

            ERDiagram diagram = erDiagramService.generateFromSql(
                    request.getSql(),
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
            log.error("Error parsing SQL", e);
            return ApiResponse.error("Failed to parse SQL: " + e.getMessage());
        }
    }

    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.success("SQL to ER service is running");
    }
}
