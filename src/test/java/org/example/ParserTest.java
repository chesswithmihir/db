package org.example;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void testCreateParserBasic() {
        // Example usage of the Parser class
        Parser parser = new Parser();
        String userInput = "create table students (id int, name string)";

        // Create a Database instance
        Database database = new Database();

        // Parse the command and execute it with the Database instance
        Command command = parser.parseCommand(userInput);
        command.execute(database); // Execute the parsed command with the Database instance

    }

    @Test
    public void testCreateParserDatabaseAndTableBasic() {
        // Example usage of the Parser class
        Parser parser = new Parser();
        String userInput = "create table students (id int, name string)";

        // Create a Database instance
        Database database = new Database();

        // Parse the command and execute it with the Database instance
        Command command = parser.parseCommand(userInput);
        command.execute(database); // Execute the parsed command with the Database instance

        // Now, let's check if the database has the expected table
        assertTrue(database.hasTable("students"));

        // You can also check other conditions related to the created table
        Table studentsTable = database.getTable("students");
        assertNotNull(studentsTable);
        assertEquals("students", studentsTable.getName());
        assertEquals(2, studentsTable.getColumns().size());
    }

    @Test
    public void testLoadParserBasic() {
        Parser parser = new Parser();
        String userInput = "load fans";

        // Create a Database instance
        Database database = new Database();

        // Parse the command and execute it with the Database instance
        Command command = parser.parseCommand(userInput);
        command.execute(database); // Execute the parsed command with the Database instance

        Table studentsTable = database.getTable("fans");
        assertTrue(database.hasTable("fans"));
        assertEquals("fans", studentsTable.getName());
        assertEquals(3, studentsTable.getColumns().size());
    }

    @Test
    public void testPrintParserBasic() {


        Parser parser = new Parser();
        Database database = new Database();

        String userInput = "load fans";
        // Parse the command and execute it with the Database instance
        Command command = parser.parseCommand(userInput);
        command.execute(database); // Execute the parsed command with the Database instance

        userInput = "print fans";
        command = parser.parseCommand(userInput);
        command.execute(database);

        Table studentsTable = database.getTable("fans");
        assertTrue(database.hasTable("fans"));
        assertEquals("fans", studentsTable.getName());
        assertEquals(3, studentsTable.getColumns().size());
    }
    @Test
    public void testStoreEmptyRowsParserBasic() {
        // Example usage of the Parser class
        Parser parser = new Parser();
        String userInput = "create table students (id int, name string)";

        // Create a Database instance
        Database database = new Database();

        // Parse the command and execute it with the Database instance
        Command command = parser.parseCommand(userInput);
        command.execute(database); // Execute the parsed command with the Database instance

        userInput = "store students";
        command = parser.parseCommand(userInput);
        command.execute(database);

        assertTrue(database.hasTable("students"));
        File file = new File("data/students.tbl");
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void testDropTableBasic() {
        Parser parser = new Parser();
        String userInput = "load fans";
        Database database = new Database();

        // Parse the command and execute it with the Database instance
        Command command = parser.parseCommand(userInput);
        command.execute(database); // Execute the parsed command with the Database instance

        assertTrue(database.hasTable("fans"));

        userInput = "drop table fans";
        command = parser.parseCommand(userInput);
        command.execute(database);

        assertFalse(database.hasTable("fans"));

    }

    @Test
    public void testInsertIntoBasic() {
        // Example usage of the Parser class
        Parser parser = new Parser();
        String userInput = "create table students (id int, name string)";

        // Create a Database instance
        Database database = new Database();

        // Parse the command and execute it with the Database instance
        Command command = parser.parseCommand(userInput);
        command.execute(database); // Execute the parsed command with the Database instance
        assertTrue(database.hasTable("students"));

        userInput = "insert into students values 12345,Mihir";
        command = parser.parseCommand(userInput);
        command.execute(database);

        assertTrue(database.hasTable("students"));
        assertEquals(database.getTable("students").getNumRows(), 1);
        assertEquals(database.getTable("students").getNumColumns(), 2);

    }

    @Test
    public void testSelectBasic() {
        Parser parser = new Parser();
        String userInput = "select Lastname from fans";
        Database database = new Database();

        Command command = parser.parseCommand(userInput);
        command.execute(database);

    }
}
