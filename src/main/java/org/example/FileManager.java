package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static Table loadTable(String tableName) throws IOException {
        String fileName = tableName + ".tbl";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String[]> rows = new ArrayList<>();

            // Read and parse each line
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(values);
            }

            // Create and populate the table
            String[] columnNames = rows.remove(0);
            List<Column> columns = new ArrayList<>();
            for (String columnName : columnNames) {
                columns.add(new Column(columnName, DataType.STRING)); // Assuming all columns are of string type
            }

            Table table = new Table(tableName, columns);

            for (String[] rowValues : rows) {
                Row row = new Row();
                for (String value : rowValues) {
                    row.addValue(value);
                }
                table.addRow(row);
            }

            return table; // Return the loaded table
        }
    }


    public static void storeTable(Table table) throws IOException {
        String fileName = table.getName() + ".tbl";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write column names
            writer.write(String.join(",", table.getColumnNames()));
            writer.newLine();

            // Write rows
            for (Row row : table.getRows()) {
                List<String> rowValues = new ArrayList<>();
                for (Object value : row.getValues()) {
                    rowValues.add(value.toString());
                }
                writer.write(String.join(",", rowValues));
                writer.newLine();
            }
        }
    }
}
