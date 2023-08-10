package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public Command parseCommand(String input) {
        String[] tokens = input.trim().split("\\s+");
        String commandType = tokens[0].toLowerCase();

        switch (commandType) {
            case "create":
                return parseCreateCommand(tokens);
            case "load":
                return parseLoadCommand(tokens);
            case "store":
                return parseStoreCommand(tokens);
            case "drop":
                return parseDropCommand(tokens);
            case "insert":
                return parseInsertCommand(tokens);
            case "print":
                return parsePrintCommand(tokens);
            case "select":
                return parseSelectCommand(tokens);
            default:
                throw new IllegalArgumentException("Unknown command type: " + commandType);
        }
    }

    private CreateTableCommand parseCreateCommand(String[] tokens) {
        if (tokens.length < 5 || !tokens[0].equalsIgnoreCase("create") || !tokens[1].equalsIgnoreCase("table")
                || !tokens[3].startsWith("(") || !tokens[tokens.length - 1].endsWith(")")) {
            throw new IllegalArgumentException("Invalid create table command format");
        }

        String tableName = tokens[2];
        String columnDefsString = String.join(" ", Arrays.copyOfRange(tokens, 3, tokens.length));
        columnDefsString = columnDefsString.substring(1, columnDefsString.length() - 1);

        String[] columnDefs = columnDefsString.split(",");

        List<Column> columns = new ArrayList<>();

        for (String columnDef : columnDefs) {
            String[] parts = columnDef.trim().split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid column definition: " + columnDef);
            }
            String columnName = parts[0];
            String typeName = parts[1];
            DataType dataType = DataType.fromString(typeName);

            Column column = new Column(columnName, dataType);
            columns.add(column);
        }

        return new CreateTableCommand(tableName, columns);
    }


    private LoadCommand parseLoadCommand(String[] tokens) {
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid load command format");
        }

        String tableName = tokens[1];
        return new LoadCommand(tableName);
    }

    private StoreCommand parseStoreCommand(String[] tokens) {
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid store command format");
        }

        String tableName = tokens[1];
        return new StoreCommand(tableName);
    }

    private DropTableCommand parseDropCommand(String[] tokens) {
        if (tokens.length != 3 || !tokens[1].equalsIgnoreCase("table")) {
            throw new IllegalArgumentException("Invalid drop table command format");
        }

        String tableName = tokens[2];
        return new DropTableCommand(tableName);
    }

    private InsertCommand parseInsertCommand(String[] tokens) {
        // Implement parsing logic for insert command
        // ...
        return null;
    }

    private PrintCommand parsePrintCommand(String[] tokens) {
        // Implement parsing logic for print command
        // ...
        return null;
    }

    private SelectCommand parseSelectCommand(String[] tokens) {
        // Implement parsing logic for select command
        // ...
        return null;
    }

    public static void main(String[] args) {
        // Example usage of the Parser class
        Parser parser = new Parser();
        String userInput = "create table students (id int, name string)";

        // Create a Database instance
        Database database = new Database();

        // Parse the command and execute it with the Database instance
        Command command = parser.parseCommand(userInput);
        command.execute(database); // Execute the parsed command with the Database instance
    }
}
