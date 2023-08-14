package org.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Database database = new Database();
        Parser parser = new Parser();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            try {
                Command command = parser.parseCommand(input);
                if (command != null) {
                    command.execute(database);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

        System.out.println("Exiting...");
    }
}
