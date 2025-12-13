package com.example.sqltoer.service;

import com.example.sqltoer.model.Column;
import com.example.sqltoer.model.Relationship;
import com.example.sqltoer.model.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class SqlParserService {

    private static final Pattern CREATE_TABLE_PATTERN =
            Pattern.compile("CREATE\\s+TABLE\\s+(?:IF\\s+NOT\\s+EXISTS\\s+)?`?([\\w_]+)`?\\s*\\(([^;]+)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    private static final Pattern COLUMN_PATTERN =
            Pattern.compile("`?([\\w_]+)`?\\s+([\\w()]+)\\s*([^,]*)", Pattern.CASE_INSENSITIVE);

    public List<Table> parseSql(String sql) {
        log.info("Starting SQL parsing");
        List<Table> tables = new ArrayList<>();

        Matcher tableMatcher = CREATE_TABLE_PATTERN.matcher(sql);

        while (tableMatcher.find()) {
            String tableName = tableMatcher.group(1);
            String tableContent = tableMatcher.group(2);

            log.info("Parsing table: {}", tableName);
            Table table = parseTable(tableName, tableContent);
            tables.add(table);
        }

        log.info("Parsed {} tables", tables.size());
        return tables;
    }

    private Table parseTable(String tableName, String content) {
        Table table = Table.builder()
                .name(tableName)
                .build();

        List<Column> columns = new ArrayList<>();
        List<String> primaryKeys = new ArrayList<>();

        String[] lines = content.split(",(?![^()]*\\))");

        for (String line : lines) {
            line = line.trim();

            if (line.toUpperCase().contains("PRIMARY KEY")) {
                parsePrimaryKey(line, primaryKeys);
            } else if (line.toUpperCase().contains("FOREIGN KEY")) {
                // Foreign key handling can be added here
            } else if (!line.toUpperCase().startsWith("KEY") &&
                       !line.toUpperCase().startsWith("INDEX") &&
                       !line.toUpperCase().startsWith("UNIQUE")) {
                Column column = parseColumn(line);
                if (column != null) {
                    columns.add(column);
                }
            }
        }

        table.setColumns(columns);
        table.setPrimaryKeys(primaryKeys);

        return table;
    }

    private Column parseColumn(String line) {
        Matcher matcher = COLUMN_PATTERN.matcher(line);

        if (matcher.find()) {
            String name = matcher.group(1);
            String type = matcher.group(2);
            String attributes = matcher.group(3);

            boolean nullable = !attributes.toUpperCase().contains("NOT NULL");
            boolean primaryKey = attributes.toUpperCase().contains("PRIMARY KEY");

            String comment = extractComment(attributes);
            String defaultValue = extractDefault(attributes);

            return Column.builder()
                    .name(name)
                    .type(type)
                    .nullable(nullable)
                    .primaryKey(primaryKey)
                    .comment(comment)
                    .defaultValue(defaultValue)
                    .build();
        }

        return null;
    }

    private void parsePrimaryKey(String line, List<String> primaryKeys) {
        Pattern pkPattern = Pattern.compile("PRIMARY\\s+KEY\\s*\\(([^)]+)\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pkPattern.matcher(line);

        if (matcher.find()) {
            String keys = matcher.group(1);
            String[] keyArray = keys.split(",");
            for (String key : keyArray) {
                primaryKeys.add(key.trim().replaceAll("`", ""));
            }
        }
    }

    private String extractComment(String attributes) {
        Pattern commentPattern = Pattern.compile("COMMENT\\s+'([^']*)'", Pattern.CASE_INSENSITIVE);
        Matcher matcher = commentPattern.matcher(attributes);
        return matcher.find() ? matcher.group(1) : null;
    }

    private String extractDefault(String attributes) {
        Pattern defaultPattern = Pattern.compile("DEFAULT\\s+([^\\s,]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = defaultPattern.matcher(attributes);
        return matcher.find() ? matcher.group(1) : null;
    }

    public List<Relationship> extractRelationships(List<Table> tables) {
        List<Relationship> relationships = new ArrayList<>();

        for (Table table : tables) {
            for (Column column : table.getColumns()) {
                if (column.isForeignKey() && column.getReferencedTable() != null) {
                    Relationship relationship = Relationship.builder()
                            .fromTable(table.getName())
                            .toTable(column.getReferencedTable())
                            .fromColumn(column.getName())
                            .toColumn(column.getReferencedColumn())
                            .type(Relationship.RelationType.MANY_TO_ONE)
                            .name(table.getName() + "_to_" + column.getReferencedTable())
                            .build();
                    relationships.add(relationship);
                }
            }
        }

        return relationships;
    }
}
