package com.example.sqltoer.service;

import com.example.sqltoer.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class AiGeneratorService {

    public ERDiagram generateFromDescription(String description, String diagramName) {
        log.info("Generating ER diagram from AI description: {}", diagramName);

        // Mock AI implementation - in production, this would call an AI service
        List<Table> tables = analyzeAndGenerateTables(description);
        List<Relationship> relationships = generateRelationships(tables, description);

        return ERDiagram.builder()
                .name(diagramName != null ? diagramName : "AI Generated Diagram")
                .description(description)
                .tables(tables)
                .relationships(relationships)
                .createdBy("AI Generator")
                .createdAt(System.currentTimeMillis())
                .build();
    }

    private List<Table> analyzeAndGenerateTables(String description) {
        List<Table> tables = new ArrayList<>();

        // Simple keyword-based analysis (mock implementation)
        if (containsKeywords(description, "用户", "user")) {
            tables.add(createUserTable());
        }

        if (containsKeywords(description, "订单", "order")) {
            tables.add(createOrderTable());
        }

        if (containsKeywords(description, "商品", "product", "产品")) {
            tables.add(createProductTable());
        }

        if (containsKeywords(description, "评论", "comment", "评价")) {
            tables.add(createCommentTable());
        }

        // If no keywords matched, create a sample table
        if (tables.isEmpty()) {
            tables.add(createSampleTable());
        }

        return tables;
    }

    private boolean containsKeywords(String text, String... keywords) {
        String lowerText = text.toLowerCase();
        return Arrays.stream(keywords).anyMatch(lowerText::contains);
    }

    private Table createUserTable() {
        return Table.builder()
                .name("user")
                .comment("用户表")
                .columns(Arrays.asList(
                        Column.builder().name("id").type("BIGINT").primaryKey(true).nullable(false).comment("用户ID").build(),
                        Column.builder().name("username").type("VARCHAR(50)").nullable(false).comment("用户名").build(),
                        Column.builder().name("email").type("VARCHAR(100)").nullable(false).comment("邮箱").build(),
                        Column.builder().name("password").type("VARCHAR(255)").nullable(false).comment("密码").build(),
                        Column.builder().name("created_at").type("DATETIME").defaultValue("CURRENT_TIMESTAMP").comment("创建时间").build()
                ))
                .primaryKeys(Arrays.asList("id"))
                .build();
    }

    private Table createOrderTable() {
        return Table.builder()
                .name("order")
                .comment("订单表")
                .columns(Arrays.asList(
                        Column.builder().name("id").type("BIGINT").primaryKey(true).nullable(false).comment("订单ID").build(),
                        Column.builder().name("user_id").type("BIGINT").nullable(false).foreignKey(true).referencedTable("user").referencedColumn("id").comment("用户ID").build(),
                        Column.builder().name("order_no").type("VARCHAR(50)").nullable(false).comment("订单号").build(),
                        Column.builder().name("total_amount").type("DECIMAL(10,2)").nullable(false).comment("总金额").build(),
                        Column.builder().name("status").type("VARCHAR(20)").defaultValue("'pending'").comment("订单状态").build(),
                        Column.builder().name("created_at").type("DATETIME").defaultValue("CURRENT_TIMESTAMP").comment("创建时间").build()
                ))
                .primaryKeys(Arrays.asList("id"))
                .build();
    }

    private Table createProductTable() {
        return Table.builder()
                .name("product")
                .comment("商品表")
                .columns(Arrays.asList(
                        Column.builder().name("id").type("BIGINT").primaryKey(true).nullable(false).comment("商品ID").build(),
                        Column.builder().name("name").type("VARCHAR(200)").nullable(false).comment("商品名称").build(),
                        Column.builder().name("price").type("DECIMAL(10,2)").nullable(false).comment("价格").build(),
                        Column.builder().name("stock").type("INT").defaultValue("0").comment("库存").build(),
                        Column.builder().name("description").type("TEXT").comment("商品描述").build(),
                        Column.builder().name("created_at").type("DATETIME").defaultValue("CURRENT_TIMESTAMP").comment("创建时间").build()
                ))
                .primaryKeys(Arrays.asList("id"))
                .build();
    }

    private Table createCommentTable() {
        return Table.builder()
                .name("comment")
                .comment("评论表")
                .columns(Arrays.asList(
                        Column.builder().name("id").type("BIGINT").primaryKey(true).nullable(false).comment("评论ID").build(),
                        Column.builder().name("user_id").type("BIGINT").nullable(false).foreignKey(true).referencedTable("user").referencedColumn("id").comment("用户ID").build(),
                        Column.builder().name("product_id").type("BIGINT").nullable(false).foreignKey(true).referencedTable("product").referencedColumn("id").comment("商品ID").build(),
                        Column.builder().name("content").type("TEXT").nullable(false).comment("评论内容").build(),
                        Column.builder().name("rating").type("INT").defaultValue("5").comment("评分").build(),
                        Column.builder().name("created_at").type("DATETIME").defaultValue("CURRENT_TIMESTAMP").comment("创建时间").build()
                ))
                .primaryKeys(Arrays.asList("id"))
                .build();
    }

    private Table createSampleTable() {
        return Table.builder()
                .name("entity")
                .comment("示例实体表")
                .columns(Arrays.asList(
                        Column.builder().name("id").type("BIGINT").primaryKey(true).nullable(false).comment("主键ID").build(),
                        Column.builder().name("name").type("VARCHAR(100)").nullable(false).comment("名称").build(),
                        Column.builder().name("description").type("TEXT").comment("描述").build(),
                        Column.builder().name("created_at").type("DATETIME").defaultValue("CURRENT_TIMESTAMP").comment("创建时间").build()
                ))
                .primaryKeys(Arrays.asList("id"))
                .build();
    }

    private List<Relationship> generateRelationships(List<Table> tables, String description) {
        List<Relationship> relationships = new ArrayList<>();

        // Auto-detect relationships based on foreign keys
        for (Table table : tables) {
            for (Column column : table.getColumns()) {
                if (column.isForeignKey() && column.getReferencedTable() != null) {
                    relationships.add(Relationship.builder()
                            .fromTable(table.getName())
                            .toTable(column.getReferencedTable())
                            .fromColumn(column.getName())
                            .toColumn(column.getReferencedColumn())
                            .type(Relationship.RelationType.MANY_TO_ONE)
                            .name(table.getName() + "_" + column.getReferencedTable() + "_fk")
                            .build());
                }
            }
        }

        return relationships;
    }
}
