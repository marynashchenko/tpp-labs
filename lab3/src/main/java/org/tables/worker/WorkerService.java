package org.tables.worker;

import org.connection.DatabaseOperation;

import java.util.List;

public class WorkerService {

    private Worker getWorker(List<String> columnNames, List<String> columnValues) {
        Worker worker = new Worker();
        for (int i = 0; i < columnNames.size(); i++) {
            switch (columnNames.get(i)) {
                case "worker_id" -> worker.setWorker_id(Integer.parseInt(columnValues.get(i)));
                case "worker_name" -> worker.setWorker_name(columnValues.get(i));
                case "worker_surname" -> worker.setWorker_surname(columnValues.get(i));
                case "worker_age" -> worker.setWorker_age(Integer.parseInt(columnValues.get(i)));
                default -> throw new IllegalStateException("Unexpected value: " + columnNames.get(i));
            }
        }
        return worker;
    }

    public void insertWorker(List<String> columnNames, List<String> columnValues){
        if(columnNames.contains("worker_id")){
            throw new IllegalArgumentException("worker_id should not be provided for insert operation");
        }

        Worker worker = getWorker(columnNames, columnValues);
        DatabaseOperation.insertItem(worker);
    }

    public void updateWorker(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("worker_id")){
            throw new IllegalArgumentException("worker_id should be provided for update operation");
        }

        Worker worker = getWorker(columnNames, columnValues);
        DatabaseOperation.updateItem(worker);
    }

    public void deleteWorker(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("worker_id")){
            throw new IllegalArgumentException("worker_id should be provided for delete operation");
        }

        Worker worker = getWorker(columnNames, columnValues);
        DatabaseOperation.deleteItem(worker);
    }
}