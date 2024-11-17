package org.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles the terminal commands for different tables in the database.
 * It provides methods to show, insert, update, and delete records in the product, shop, and city tables.
 */
public class Terminal {

    public static void main(String[] args) {

        // For the 'profession' table
        // insert profession (profession_id="2", profession_name="Engineer", profession_description="Engineer description", workers_id="1")
        // update profession (profession_id="2", profession_name="Doctor", profession_description="Doctor description", workers_id="1")
        // delete profession (profession_id="2")

        // For the 'worker' table
        // insert worker (worker_id="2", worker_name="John", worker_surname="Smith", worker_age="30")
        // update worker (worker_id="2", worker_name="Mike", worker_surname="Smith", worker_age="30")
        // delete worker (worker_id="2")

        // For the 'zhek' table
        // insert zhek (zhek_id="2", zhek_district="Zhek2", zhek_address="Address2", professions_id="1 2")
        // update zhek (zhek_id="2", zhek_district="Zhek2", zhek_address="Address2", professions_id="1 2")
        // delete zhek (zhek_id="2")

        // To show all records in a table
        // show profession
        // show worker
        // show zhek


        try {
            while (true) {

                System.out.println("Enter command:");

                Scanner scanner = new Scanner(System.in);

                String userInput = scanner.nextLine();

                if (userInput.equals("exit")) {
                    System.exit(0);
                }

                String[] userInputArray = userInput.split(" ");

                String command = userInputArray[0];

                if (command.equals("exit")) {
                    System.exit(0);
                }

                String tableName = userInputArray[1];


                List<String> columnNames = new ArrayList<>();
                List<String> columnValues = new ArrayList<>();

                //show all records in a table
                if (command.equals("show")) {
                    TerminalMessageHandler.show(tableName);
                } else {
                    //insert, update, or delete a record in a table
                    Pattern columnPattern = Pattern.compile("([a-zA-Z_]+)=\"([^\"]+)\"");

                    Matcher matcher = columnPattern.matcher(userInput);

                    int index = 0;
                    while (matcher.find()) {
                        columnNames.add(matcher.group(1));
                        columnValues.add(matcher.group(2));
                        index++;
                    }

                    for (int i = 0; i < columnNames.size(); i++) {
                        System.out.println(columnNames.get(i) + ": " + columnValues.get(i));
                    }
                    switch (command) {

                        case "insert" -> TerminalMessageHandler.insert(tableName, columnNames, columnValues);
                        case "update" -> TerminalMessageHandler.update(tableName, columnNames, columnValues);
                        case "delete" -> TerminalMessageHandler.delete(tableName, columnNames, columnValues);

                        default -> System.out.println("Invalid command");
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Invalid command or syntax");
            //e.printStackTrace();
            //restart the program
            main(args);
        }


    }
}
