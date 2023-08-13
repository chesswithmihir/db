package org.example;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database instance = new Database();
    private Map<String, Table> tables;

    public Database() {
        tables = new HashMap<>();
    }

    public static Database getInstance() {
        return instance;
    }

    public void addTable(Table table) {
        tables.put(table.getName(), table);
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    public void removeTable(String tableName) {
        tables.remove(tableName);
    }

    public boolean hasTable(String tableName) {
        return tables.containsKey(tableName);
    }
}
