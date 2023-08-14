package org.example;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private List<String> selectColumns;
    private List<String> fromTables;
    private List<String> whereConditions;

    public Query(List<String> selectColumns, List<String> fromTables, List<String> whereConditions) {
        this.selectColumns = selectColumns;
        this.fromTables = fromTables;
        this.whereConditions = whereConditions;
    }


    private int compareValues(Object value1, Object value2) {
        if (value1 instanceof Integer && value2 instanceof Integer) {
            return ((Integer) value1).compareTo((Integer) value2);
        } else if (value1 instanceof Float && value2 instanceof Float) {
            return ((Float) value1).compareTo((Float) value2);
        } else if (value1 instanceof String && value2 instanceof String) {
            return ((String) value1).compareTo((String) value2);
        } else {
            throw new IllegalArgumentException("Incompatible types for comparison: " + value1.getClass() + " and " + value2.getClass());
        }
    }

    private List<Row> applyWhereCondition(List<Row> rows, String condition, List<Table> selectedTables) {
        // Split the condition into individual components (e.g., "column operator value")
        String[] components = condition.split(" ");
        String columnName = components[0];
        String operator = components[1];
        String value = components[2];

        List<Row> filteredRows = new ArrayList<>();
        for (Row row : rows) {
            int columnIndex = -1;
            for (Table table : selectedTables) {
                columnIndex = table.getColumnNames().indexOf(columnName);
                if (columnIndex != -1) {
                    break;
                }
            }
            if (columnIndex == -1) {
                throw new IllegalArgumentException("Column not found: " + columnName);
            }

            Object rowValue = row.getValue(columnIndex);
            boolean conditionMet = false;

            if (operator.equals("=")) {
                conditionMet = rowValue.equals(DataType.fromString(value).parseValue(value));
            } else if (operator.equals("<")) {
                conditionMet = compareValues(rowValue, DataType.fromString(value).parseValue(value)) < 0;
            } else if (operator.equals(">")) {
                conditionMet = compareValues(rowValue, DataType.fromString(value).parseValue(value)) > 0;
            }

            if (conditionMet) {
                filteredRows.add(row);
            }
        }
        return filteredRows;
    }
    public Table execute(Database database) {
        // Step 1: Retrieve tables from the database
        List<Table> selectedTables = new ArrayList<>();
        for (String tableName : fromTables) {
            Table table = database.getTable(tableName);
            if (table == null) {
                throw new IllegalArgumentException("Table not found: " + tableName);
            }
            selectedTables.add(table);
        }

        // Step 2: Apply WHERE conditions if present
        List<Row> filteredRows = selectedTables.get(0).getRows(); // Initialize with the first table's rows
        if (whereConditions != null) {
            for (String condition : whereConditions) {
                filteredRows = applyWhereCondition(filteredRows, condition, selectedTables);
            }
        }

        // Step 3: Create a new result table with selected columns
        List<Column> resultColumns = new ArrayList<>();
        for (String columnName : selectColumns) {
            // TODO: Find the appropriate column from the selected tables and add it to resultColumns
            // You might need to handle column name conflicts here
            Column resultColumn = null;
            for (Table table : selectedTables) {
                for (Column column : table.getColumns()) {
                    if (column.getName().equals(columnName)) {
                        resultColumn = new Column(columnName, column.getDataType());
                        break;
                    }
                }
                if (resultColumn != null) {
                    break;
                }
            }
            if (resultColumn == null) {
                throw new IllegalArgumentException("Column not found: " + columnName);
            }
            resultColumns.add(resultColumn);
        }
        Table resultTable = new Table("result", resultColumns);

        // Step 4: Populate the result table with selected columns and filtered rows
        for (Row row : filteredRows) {
            Row resultRow = new Row();
            for (String columnName : selectColumns) {
                // TODO: Find the corresponding value from the filtered rows and add it to resultRow
                Object value = null;
                for (Table table : selectedTables) {
                    for (Column column : table.getColumns()) {
                        if (column.getName().equals(columnName)) {
                            int columnIndex = table.getColumnNames().indexOf(columnName);
                            value = row.getValue(columnIndex);
                            break;
                        }
                    }
                    if (value != null) {
                        break;
                    }
                }
                resultRow.addValue(value);
            }
            resultTable.addRow(resultRow);
        }

        // Step 5: Return the result table
        return resultTable;
    }


}
