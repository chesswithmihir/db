# Database Management System Architecture Design

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

This architectural design provides a comprehensive overview of the project's structure, user interaction, data storage, processing, and output. As you start implementing each component, you can follow this design to create a well-structured and functional database management system.

