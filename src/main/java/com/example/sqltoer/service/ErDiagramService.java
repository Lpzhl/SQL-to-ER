package com.example.sqltoer.service;

import com.example.sqltoer.model.ERDiagram;
import com.example.sqltoer.model.Relationship;
import com.example.sqltoer.model.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ErDiagramService {

    private final SqlParserService sqlParserService;

    public ERDiagram generateFromSql(String sql, String diagramName) {
        log.info("Generating ER diagram from SQL: {}", diagramName);

        List<Table> tables = sqlParserService.parseSql(sql);
        List<Relationship> relationships = sqlParserService.extractRelationships(tables);

        return ERDiagram.builder()
                .name(diagramName != null ? diagramName : "Untitled Diagram")
                .description("Generated from SQL")
                .tables(tables)
                .relationships(relationships)
                .createdBy("SQL Parser")
                .createdAt(System.currentTimeMillis())
                .build();
    }

    public String generateGraphData(ERDiagram diagram) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"nodes\": [\n");

        for (int i = 0; i < diagram.getTables().size(); i++) {
            Table table = diagram.getTables().get(i);
            sb.append("    {\n");
            sb.append("      \"id\": \"").append(table.getName()).append("\",\n");
            sb.append("      \"label\": \"").append(table.getName()).append("\",\n");
            sb.append("      \"type\": \"table\",\n");
            sb.append("      \"columns\": ").append(table.getColumns().size()).append("\n");
            sb.append("    }");
            if (i < diagram.getTables().size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append("  ],\n");
        sb.append("  \"edges\": [\n");

        for (int i = 0; i < diagram.getRelationships().size(); i++) {
            Relationship rel = diagram.getRelationships().get(i);
            sb.append("    {\n");
            sb.append("      \"from\": \"").append(rel.getFromTable()).append("\",\n");
            sb.append("      \"to\": \"").append(rel.getToTable()).append("\",\n");
            sb.append("      \"type\": \"").append(rel.getType().getSymbol()).append("\"\n");
            sb.append("    }");
            if (i < diagram.getRelationships().size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append("  ]\n");
        sb.append("}");

        return sb.toString();
    }
}
