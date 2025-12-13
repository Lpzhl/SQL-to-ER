package com.example.sqltoer.util;

import com.example.sqltoer.model.ERDiagram;
import com.example.sqltoer.model.Relationship;
import com.example.sqltoer.model.Table;

public class ErDiagramValidator {

    public static boolean validate(ERDiagram diagram) {
        if (diagram == null) {
            return false;
        }

        if (diagram.getTables() == null || diagram.getTables().isEmpty()) {
            return false;
        }

        for (Table table : diagram.getTables()) {
            if (table.getName() == null || table.getName().trim().isEmpty()) {
                return false;
            }

            if (table.getColumns() == null || table.getColumns().isEmpty()) {
                return false;
            }
        }

        if (diagram.getRelationships() != null) {
            for (Relationship rel : diagram.getRelationships()) {
                if (!isValidRelationship(rel, diagram)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isValidRelationship(Relationship rel, ERDiagram diagram) {
        if (rel.getFromTable() == null || rel.getToTable() == null) {
            return false;
        }

        boolean fromExists = diagram.getTables().stream()
                .anyMatch(t -> t.getName().equals(rel.getFromTable()));

        boolean toExists = diagram.getTables().stream()
                .anyMatch(t -> t.getName().equals(rel.getToTable()));

        return fromExists && toExists;
    }
}
