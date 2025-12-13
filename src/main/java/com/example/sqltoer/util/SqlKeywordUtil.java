package com.example.sqltoer.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SqlKeywordUtil {

    private static final Set<String> SQL_KEYWORDS = new HashSet<>(Arrays.asList(
            "CREATE", "TABLE", "ALTER", "DROP", "PRIMARY", "KEY", "FOREIGN",
            "REFERENCES", "INDEX", "UNIQUE", "NOT", "NULL", "DEFAULT",
            "AUTO_INCREMENT", "COMMENT", "ENGINE", "CHARSET", "COLLATE"
    ));

    private static final Set<String> DATA_TYPES = new HashSet<>(Arrays.asList(
            "INT", "INTEGER", "BIGINT", "SMALLINT", "TINYINT",
            "VARCHAR", "CHAR", "TEXT", "LONGTEXT", "MEDIUMTEXT",
            "DECIMAL", "NUMERIC", "FLOAT", "DOUBLE",
            "DATE", "DATETIME", "TIMESTAMP", "TIME",
            "BOOLEAN", "BOOL", "BLOB", "JSON"
    ));

    public static boolean isKeyword(String word) {
        return SQL_KEYWORDS.contains(word.toUpperCase());
    }

    public static boolean isDataType(String word) {
        String type = word.toUpperCase();
        for (String dataType : DATA_TYPES) {
            if (type.startsWith(dataType)) {
                return true;
            }
        }
        return false;
    }

    public static String sanitizeIdentifier(String identifier) {
        if (identifier == null) {
            return null;
        }
        return identifier.replaceAll("`", "").trim();
    }
}
