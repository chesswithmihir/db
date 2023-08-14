package org.example;

public class DropTableCommand implements Command {
    private String tableName;

    public DropTableCommand(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public Table execute(Database database) {
        Table table = database.getTable(tableName);
        if (table != null) {
            database.removeTable(tableName);
            System.out.println("Table " + tableName + " dropped successfully.");
        } else {
            System.err.println("Table " + tableName + " does not exist.");
        }
        return table;
    }
}
