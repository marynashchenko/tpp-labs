package org.connection;

import org.tables.Profession;
import org.tables.Worker;
import org.tables.Zhek;

import java.util.List;

/**
 * This class handles terminal messages for different tables in the database.
 * It provides methods to show, insert, update, and delete records in the product, shop, and city tables.
 */
public class TerminalMessageHandler {

    /**
     * This method shows all records in the specified table.
     *
     * @param tableName The name of the table to show records from.
     */
    public static void show(String tableName) {
        switch (tableName) {
            case "worker":
                Worker worker = new Worker();
                worker.show();
                break;
            case "Profession":
                Profession profession = new Profession();
                profession.show();
                break;
            case "zhek":
                Zhek zhek = new Zhek();
                zhek.show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tableName);
        }
    }

    /**
     * This method inserts a new record into the specified table.
     *
     * @param tableName    The name of the table to insert a record into.
     * @param columnNames  The names of the columns to insert values into.
     * @param columnValues The values to insert into the specified columns.
     */
    public static void insert(String tableName, List<String> columnNames, List<String> columnValues) {
        switch (tableName) {
            case "worker":
                Worker worker = new Worker(columnNames, columnValues);
                worker.insert();
                break;
            case "Profession":
                Profession profession = new Profession(columnNames, columnValues);
                profession.insert();
                break;
            case "zhek":
                Zhek zhek = new Zhek(columnNames, columnValues);
                zhek.insert();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tableName);

        }
    }

    /**
     * This method updates a record in the specified table.
     *
     * @param tableName    The name of the table to update a record in.
     * @param columnNames  The names of the columns to update.
     * @param columnValues The new values for the specified columns.
     */
    public static void update(String tableName, List<String> columnNames, List<String> columnValues) {
        switch (tableName) {
            case "worker":
                Worker worker = new Worker(columnNames, columnValues);
                worker.update();
                break;
            case "Profession":
                Profession profession = new Profession(columnNames, columnValues);
                profession.update();
                break;
            case "zhek":
                Zhek zhek = new Zhek(columnNames, columnValues);
                zhek.update();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tableName);
        }
    }

    /**
     * This method deletes a record from the specified table.
     *
     * @param tableName    The name of the table to delete a record from.
     * @param columnNames  The names of the columns in the record to delete.
     * @param columnValues The values in the record to delete.
     */
    public static void delete(String tableName, List<String> columnNames, List<String> columnValues) {
        switch (tableName) {

            case "worker":
                Worker worker = new Worker(columnNames, columnValues);
                worker.delete();
                break;
            case "Profession":
                Profession profession = new Profession(columnNames, columnValues);
                profession.delete();
                break;
            case "zhek":
                Zhek zhek = new Zhek(columnNames, columnValues);
                zhek.delete();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tableName);
        }
    }
}