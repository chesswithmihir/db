package org.example;

import java.io.IOException;
import java.lang.reflect.Array;
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
        // TODO create table <table name> (<column0 name> <type0>, <column1 name> <type1>, ...)

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
        // TODO load <table name>
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
        // TODO insert into <table name> values <literal0>,<literal1>,...

        if (tokens.length < 5 || !tokens[0].equalsIgnoreCase("insert") || !tokens[1].equalsIgnoreCase("into")
                || !tokens[3].equalsIgnoreCase("values")) {
            throw new IllegalArgumentException("Invalid insert command format");
        }

        String tableName = tokens[2];
        List<String> values = new ArrayList<>(Arrays.asList(tokens).subList(4, tokens.length));
        values = new ArrayList<>(Arrays.asList(values.get(0).split(",")));
        return new InsertCommand(tableName, values);
    }


    private PrintCommand parsePrintCommand(String[] tokens) {
        // TODO print <table name>
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid store command format");
        }

        String tableName = tokens[1];
        return new PrintCommand(tableName);

    }
    private Query parseQuery(String[] tokens) {
        // Implement the logic to parse the input tokens and create a Query instance
        // Parse the SELECT columns, FROM tables, and WHERE conditions
        // ...

        System.out.println(tokens);
//        List<String> selectColumns = ...; // Parsed list of SELECT columns
//        List<String> fromTables = ...;    // Parsed list of FROM tables
//        List<String> whereConditions = ...; // Parsed list of WHERE conditions

        // return new Query(selectColumns, fromTables, whereConditions);
        return null;
    }
    private SelectCommand parseSelectCommand(String[] tokens) {
        // TODO: select <column expr0>,<column expr1>,... from <table0>,<table1>,... where <cond0> and <cond1> and ...
        if (tokens.length < 4 || !tokens[0].equalsIgnoreCase("select")) {
            throw new IllegalArgumentException("Invalid insert command format");
        }

        ArrayList<String> listOfTokens = new ArrayList<>();
        for (String s : tokens) {
            listOfTokens.add(s.toLowerCase());
        }

        ArrayList<String> selectColumns = new ArrayList<>();
        int i;
        for (i = 1; i < listOfTokens.size(); i++) {
            String currWord = listOfTokens.get(i);
            if (currWord.equals("from")) {
                break;
            }
            selectColumns.add(currWord);
        }

        List<String> fromTables = new ArrayList<>();
        for (; i < listOfTokens.size(); i++) {
            String currWord = listOfTokens.get(i);
            if (currWord == "where") {
                break;
            }
            fromTables.add(currWord);
        }

        List<String> whereConditions = null; // Parsed list of WHERE conditions
        // add logic for where condition,
        // use filtering as the last step
        if (listOfTokens.contains("where")) {
            // filter
            whereConditions = new ArrayList<>();
            for (; i < listOfTokens.size(); i++) {
                String currWord = listOfTokens.get(i);
                whereConditions.add(currWord);
            }
        }

        Query query = new Query(selectColumns, fromTables, whereConditions);
        return new SelectCommand(query);
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
