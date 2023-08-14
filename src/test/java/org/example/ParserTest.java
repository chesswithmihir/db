package org.example;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;

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
        String userInput = "load fans";
        Database database = new Database();

        // Parse the command and execute it with the Database instance
        Command command = parser.parseCommand(userInput);
        command.execute(database); // Execute the parsed command with the Database instance

        assertTrue(database.hasTable("fans"));

        parser = new Parser();
        userInput = "select Lastname from fans";
        command = parser.parseCommand(userInput);
        Table result = ((SelectCommand) command).execute(database); // Cast the command to SelectCommand and call execute

        // Expected output
        String expectedOutput = "Lastname\n'Lee'\n'Lee'\n'Ray'\n'Hwang'\n'Rulison'\n'Fang'\n";

        // Actual output
        String actualOutput = result.toString();

        // Compare expected and actual outputs
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSelectMedium() {
        Parser parser = new Parser();
        String userInput = "load fans";
        Database database = new Database();

        Command command = parser.parseCommand(userInput);
        command.execute(database);

        assertTrue(database.hasTable("fans"));

        parser = new Parser();
        userInput = "select Lastname from fans where Lastname = 'Hwang'";
        command = parser.parseCommand(userInput);
        command.execute(database);



    }

}
