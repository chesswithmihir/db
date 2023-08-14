# Database Management System Architecture Design

## Commands

The commands include:

1. `create table`
2. `load`
3. `store`
4. `drop table`
5. `insert into`
6. `print`
7. `select`

## An example

```commandline
> load fans
Table fans loaded successfully.
> print fans
Lastname string,Firstname string,TeamName string
Lee,Maurice,Mets
Lee,Maurice,Steelers
Ray,Mitas,Patriots
Hwang,Alex,Cloud9
Rulison,Jared,EnVyUs
Fang,Vivian,Golden Bears
> create table students (id int, name string)
Table students created successfully.
> print students
id int,name string
> insert into students values 123,'Mihir'
Row inserted successfully
> print students
id int,name string
123,''Mihir''
> select Lastname from fans
Processing column: Lastname
Lastname
'Lee'
'Lee'
'Ray'
'Hwang'
'Rulison'
'Fang'

> select Lastname from fans where Lastname = 'Hwang'
Processing column: Lastname
Lastname
'Hwang'

> select Lastname from fans where Lastname = 'Lee'
Processing column: Lastname
Lastname
'Lee'
'Lee'
```

## Project Overview

### User Interaction:

1. **User Input**: Users interact with the database system through a command-line interface. They input various commands to perform actions such as creating tables, inserting data, querying, and more.

2. **Command Parser**: The system contains a command parser that processes user input. The parser recognizes different commands and their arguments.

### Data Storage:

1. **Table Management**: The core of the system is the `Table` class. Each instance of this class represents a database table. It stores columns, rows, and associated data.

2. **Column Definition**: The `Column` class defines the attributes of each column, such as its name, data type, and constraints.

3. **Row Data**: Rows in each table are represented using the `Row` class. Each row contains cell values corresponding to each column.

### Command Processing:

1. **Command Processing Engine**: The system processes commands entered by the user through the command-line interface.

2. **Command Class**: The `Command` class represents user commands. It holds information about the command type, arguments, and parameters.

3. **Command Executor**: The system includes a command executor that takes a `Command` instance and performs the appropriate action based on the command type.

### Data Manipulation:

1. **Create Table Command**:
    - User can create a new table with specified column definitions.
    - The `Table` class is used to store column and row data.

2. **Insert Into Command**:
    - Users can insert rows of data into existing tables.
    - The `Table` class handles row insertion and ensures data integrity.

3. **Select Command**:
    - Users can perform SELECT queries to retrieve data from one or more tables.
    - The system processes the query, performs table joins (if applicable), and filters data based on conditions.

### Data Persistence:

1. **Load and Store**:
    - The system includes a `FileManager` class to manage loading and storing table data to/from files.
    - Users can use the `load` and `store` commands to manage table data persistence.

### Output:

1. **Print Command**:
    - Users can use the `print` command to display the content of a table.
    - The system outputs the table data in a user-readable format.

2. **Query Result**:
    - The result of a `SELECT` query is displayed to the user in a structured format.
    - This result includes the selected columns and rows based on query conditions.

### Flow of Execution:

1. **User Input Handling**:
    - User input is processed by the command parser.
    - Parsed commands are passed to the command executor.

2. **Command Execution**:
    - The command executor identifies the command type and delegates execution to appropriate methods.

3. **Data Manipulation**:
    - Depending on the command type, the system manipulates table data accordingly.
    - Columns and rows are managed using the `Column`, `Row`, and `Table` classes.

4. **Data Persistence**:
    - Data can be loaded from files using the `FileManager` class.
    - Data can be stored to files using the same class.

5. **Output Generation**:
    - Resulting data from queries or other commands is presented to the user in a readable format.
    - The `print` command directly displays the table content.

### Benefits of the Architecture:

- Modular Structure: The system is modular, with distinct classes responsible for different aspects of the database management process.
- Flexibility: The architecture allows for easy addition of new commands and features.
- Separation of Concerns: Each class has a specific role, improving code organization and maintainability.
- Data Integrity: The system ensures data integrity by managing column constraints and handling errors.

This architectural design provides a comprehensive overview of the project's structure, user interaction, data storage, processing, and output.

## ACID Properties

### Atomicity:

- **Design Aspect**: The architecture includes a command processing engine and a command executor. These components ensure that commands are executed as atomic units of work.

- **How it Aligns**: The system processes each command in its entirety or not at all. If an error occurs during command execution, the system rolls back any changes made, ensuring that the database remains in a consistent state.

### Consistency:

- **Design Aspect**: The system enforces data integrity through column constraints, validation checks, and error handling. It also provides mechanisms to ensure data consistency across tables.

- **How it Aligns**: The system ensures that the database remains in a consistent state by validating data before insertion and enforcing primary key constraints. It prevents data corruption by rejecting invalid commands or data that could lead to inconsistency.

### Isolation:

- **Design Aspect**: The architecture doesn't explicitly mention isolation, but isolation is a crucial aspect of a database system. It ensures that multiple transactions can occur concurrently without interfering with each other.

- **How it Aligns**: While the architectural design doesn't provide explicit details on transaction isolation, it's an important consideration when implementing the data manipulation and storage components. Isolation ensures that concurrent transactions don't result in conflicts or data inconsistencies.

### Durability:

- **Design Aspect**: The `FileManager` class handles data persistence to files, ensuring that changes made to the database are durable even after system shutdown.

- **How it Aligns**: The architectural design aligns with durability by persisting data changes to files. This ensures that data modifications made by users are stored safely and can be retrieved even after a system crash or restart.

## Java Classes

1. `App`: The entry point of the application where the program initiates interaction with the database.

2. `Column`: A class representing the columns in a table, containing information like column name and data type.

3. `Command`: A base class representing database commands. It should be extended by specific command classes like `CreateTableCommand`, `LoadCommand`, etc.

4. `Database`: A class to manage the tables and execute commands within the database.

5. `DataType`: A class representing data types, associating custom type names with Java types.

6. `FileManager`: A class responsible for loading and storing data to files.

7. `Parser`: A class responsible for parsing user input and creating corresponding command objects.

8. `Query`: A class for handling select queries and filtering rows based on conditions (optional).

9. `Row`: A class representing a row in a table.

10. `Table`: A class representing a table in the database, containing columns and rows.

11. `CreateTableCommand`: A class representing the "create table" command. Extends `Command`.

12. `LoadCommand`: A class representing the "load" command. Extends `Command`.

13. `StoreCommand`: A class representing the "store" command. Extends `Command`.

14. `DropTableCommand`: A class representing the "drop table" command. Extends `Command`.

15. `InsertIntoCommand`: A class representing the "insert into" command. Extends `Command`.

16. `PrintCommand`: A class representing the "print" command. Extends `Command`.

17. `SelectCommand`: A class representing the "select" command. Extends `Command`. (optional)

