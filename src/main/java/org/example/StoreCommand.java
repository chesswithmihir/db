package org.example;

public class StoreCommand implements Command {
    private String tableName;

    public StoreCommand(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void execute(Database database) {
        // Implement the logic to store the table in the database to a file
        // You will need to get the table from the database, serialize it, and write to a file
        // ...
    }
}
