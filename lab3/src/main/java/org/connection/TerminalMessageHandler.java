package org.connection;


import org.tables.profession.ProfessionService;
import org.tables.worker.WorkerService;
import org.tables.zhek.ZhekService;

import java.util.List;

/**
 * This class handles terminal messages for different operations on the database tables.
 */
public class TerminalMessageHandler {

    /**
     * This method shows all items in the specified table.
     * @param tableName The name of the table.
     */
    public static void show(String tableName) {
        switch (tableName) {
            case "worker":
                DatabaseOperation.readAllItems("Worker");
                break;

            case "profession":
                DatabaseOperation.readAllItems("Profession");
                break;

            case "zhek":
                DatabaseOperation.readAllItems("Zhek");
                break;

            default:
                System.out.println("Table name is not correct");
        }
    }

    /**
     * This method inserts a new item into the specified table.
     * @param tableName The name of the table.
     * @param columnNames The names of the columns.
     * @param columnValues The values for the columns.
     */
    public static void insert(String tableName, List<String> columnNames, List<String> columnValues) {
        switch (tableName) {
            case "worker":
                WorkerService workerService = new WorkerService();
                workerService.insertWorker(columnNames, columnValues);
                break;

            case "zhek":
                ZhekService zhekService = new ZhekService();
                zhekService.insertZhek(columnNames, columnValues);
                break;

            case "profession":
                ProfessionService professionService = new ProfessionService();
                professionService.insertProfession(columnNames, columnValues);
                break;

            default:
                System.out.println("Table name is not correct");
        }
    }

    /**
     * This method updates an existing item in the specified table.
     * @param tableName The name of the table.
     * @param columnNames The names of the columns.
     * @param columnValues The new values for the columns.
     */
    public static void update(String tableName, List<String> columnNames, List<String> columnValues) {
        switch (tableName) {
            case "worker":
                WorkerService workerService = new WorkerService();
                workerService.updateWorker(columnNames, columnValues);
                break;

            case "shop":
            case "zhek":
                ZhekService zhekService = new ZhekService();
                zhekService.updateZhek(columnNames, columnValues);
                break;

            case "profession":
                ProfessionService professionService = new ProfessionService();
                professionService.updateProfession(columnNames, columnValues);
                break;

            default:
                System.out.println("Table name is not correct");
        }
    }

    /**
     * This method deletes an existing item from the specified table.
     * @param tableName The name of the table.
     * @param columnNames The names of the columns.
     * @param columnValues The values for the columns.
     */
    public static void delete(String tableName, List<String> columnNames, List<String> columnValues) {
        switch (tableName) {
            case "worker":
                WorkerService workerService = new WorkerService();
                workerService.deleteWorker(columnNames, columnValues);
                break;

            case "zhek":
                ZhekService zhekService = new ZhekService();
                zhekService.deleteZhek(columnNames, columnValues);
                break;

            case "profession":
                ProfessionService professionService = new ProfessionService();
                professionService.deleteProfession(columnNames, columnValues);
                break;

            default:
                System.out.println("Table name is not correct");
        }
    }
}