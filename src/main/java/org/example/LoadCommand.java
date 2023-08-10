package org.example;

import java.io.IOException;

public class LoadCommand implements Command {
    private String tableName;

    public LoadCommand(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void execute(Database database) {
        try {
            Table table = FileManager.loadTable(tableName);
            database.addTable(table);
            System.out.println("Table " + tableName + " loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading table " + tableName + ": " + e.getMessage());
        }
    }
}
