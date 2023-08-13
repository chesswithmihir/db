package org.example;

import java.io.IOException;

public class StoreCommand implements Command {
    private String tableName;

    public StoreCommand(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void execute(Database database) {
        try {
            Table table = database.getTable(tableName);
            FileManager.storeTable(table);
            System.out.println("Table " + tableName + " stored successfully.");
        } catch (IOException e) {
            System.err.println("Error storing table " + tableName + ": " + e.getMessage());
        }
    }
}
