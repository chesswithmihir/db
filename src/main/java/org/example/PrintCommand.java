package org.example;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class PrintCommand implements Command {
    private String tableName;

    public PrintCommand(String tableName) {
        this.tableName = tableName;
    }
    @Override
    public void execute(Database database) {
        Table table = database.getTable(tableName);
        if (table == null) {
            System.out.println("Table not found: " + tableName);
            return;
        }

        List<Column> columns = table.getColumns();
        List<String> columnString = new ArrayList<>();
        for (Column column : columns) {
            if (column.getDataType() == DataType.INT) {
                columnString.add(column.getName() + " int");
            } else if (column.getDataType() == DataType.STRING) {
                columnString.add(column.getName() + " string");
            } else if (column.getDataType() == DataType.FLOAT) {
                columnString.add(column.getName() + " float");
            } else {
                System.out.println("Invalid Type used. Must use type string, int, or float.");
            }
        }
        System.out.println(String.join(",", columnString));

        List<Row> rows = table.getRows();
        for (Row row : rows) {
            List<String> rowValues = new ArrayList<>();
            for (Object value : row.getValues()) {
                rowValues.add(value.toString());
            }
            System.out.println(String.join(",", rowValues));
        }
    }
}
