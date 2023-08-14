package org.example;

import java.util.List;

public class CreateTableCommand implements Command {
    private String tableName;
    private List<Column> columns;

    public CreateTableCommand(String tableName, List<Column> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    @Override
    public Table execute(Database database) {
        Table newTable = new Table(tableName, columns);
        database.addTable(newTable);
        System.out.println("Table " + tableName + " created successfully.");
        return newTable;
    }
}
