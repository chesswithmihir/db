package org.example;

import java.util.List;

public class InsertCommand implements Command {
    private String tableName;
    private List<String> values;

    public InsertCommand(String tableName, List<String> values) {
        this.tableName = tableName;
        this.values = values;
    }

    @Override
    public Table execute(Database database) {
        Table table = database.getTable(tableName);
        if (table == null) {
            System.out.println("Table not found: " + tableName);
            return table;
        }

        List<Column> columns = table.getColumns();

        if (values.size() != columns.size()) {
            System.out.println("Number of values doesn't match number of columns");
            return table;
        }

        Row row = new Row();

        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            String valueString = values.get(i);

            try {
                Object value = column.getDataType().toValueString(valueString);
                row.addValue(value);
            } catch (IllegalArgumentException e) {
                System.out.println("Error parsing value: " + e.getMessage());
                return table;
            }
        }

        table.addRow(row);
        System.out.println("Row inserted successfully");
        return table;
    }
}
