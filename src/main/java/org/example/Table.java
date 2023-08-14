package org.example;

import java.util.ArrayList;
import java.util.List;

public class Table {

    /**
     * name: Represents the name of the table.
     * columns: A list of Column objects that define the columns of the table.
     * rows: A list of Row objects that represent the data rows in the table.
     * */
    private String name;
    private List<Column> columns;
    private List<Row> rows;

    /**
     * @param name
     * @param columns
     * Constructor for Table
     */
    public Table(String name, List<Column> columns) {
        this.name = name;
        this.columns = new ArrayList<>(columns);
        this.rows = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Column> getColumns() {
        return new ArrayList<>(columns);
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public List<Row> getRows() {
        return new ArrayList<>(rows);
    }

    public int getNumRows() {
        return rows.size();
    }

    public int getNumColumns() {
        return columns.size();
    }

    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();
        for (Column column : columns) {
            columnNames.add(column.getName());
        }
        return columnNames;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Add column names
        sb.append(String.join(",", getColumnNames())).append("\n");

        // Add rows
        for (Row row : getRows()) {
            List<String> rowValues = new ArrayList<>();
            for (Object value : row.getValues()) {
                rowValues.add(value.toString());
            }
            sb.append("'").append(String.join("','", rowValues)).append("'\n");
        }

        return sb.toString();
    }

}
