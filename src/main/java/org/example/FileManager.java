package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String DATA_DIRECTORY = "./data/";

    public static Table loadTable(String tableName) throws IOException {
        String fileName = DATA_DIRECTORY + tableName + ".tbl";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String columnNamesLine = reader.readLine(); // Read the first line with column names and types
            String[] columnNamesAndTypes = columnNamesLine.split(",");

            List<Column> columns = new ArrayList<>();
            for (String columnInfo : columnNamesAndTypes) {
                String[] parts = columnInfo.trim().split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid column definition: " + columnInfo);
                }
                String columnName = parts[0];
                String typeName = parts[1];
                DataType dataType = DataType.fromString(typeName); // Convert type name to DataType
                Column column = new Column(columnName, dataType);
                columns.add(column);
            }

            Table table = new Table(tableName, columns);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowValues = line.split(",");
                Row row = new Row();
                for (int i = 0; i < rowValues.length; i++) {
                    row.addValue(columns.get(i).getDataType().parseValue(rowValues[i]));
                }
                table.addRow(row);
            }

            return table; // Return the loaded table
        }
    }



    public static void storeTable(Table table) throws IOException {
        String fileName = DATA_DIRECTORY + table.getName() + ".tbl";
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
