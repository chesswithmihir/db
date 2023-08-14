package org.example;

public interface Command {
    Table execute(Database database);
}
