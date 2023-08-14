package org.example;

public class SelectCommand implements Command {
    private Query query;

    public SelectCommand(Query query) {
        this.query = query;
    }

    @Override
    public void execute(Database database) {
        Table resultTable = query.execute(database);
        if (resultTable != null) {
            System.out.println(resultTable); // Print the result table
        }
    }
}
