package org.tables.worker;

import org.connection.DatabaseOperation;
import org.springframework.stereotype.Service;
import org.tables.common.BaseService;

import java.util.List;

@Service
public class WorkerService extends BaseService<Worker> {

    @Override
    public Worker get(List<String> columnNames, List<String> columnValues) {
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

    @Override
    public void insert(List<String> columnNames, List<String> columnValues) {
        if (columnNames.contains("worker_id")) {
            throw new IllegalArgumentException("worker_id should not be provided for insert operation");
        }

        Worker worker = get(columnNames, columnValues);
        DatabaseOperation.insertItem(worker);
    }

    @Override
    public void update(List<String> columnNames, List<String> columnValues) {
        if (!columnNames.contains("worker_id")) {
            throw new IllegalArgumentException("worker_id should be provided for update operation");
        }

        Worker worker = get(columnNames, columnValues);
        DatabaseOperation.updateItem(worker);
    }

    @Override
    public void delete(List<String> columnNames, List<String> columnValues) {
        if (!columnNames.contains("worker_id")) {
            throw new IllegalArgumentException("worker_id should be provided for delete operation");
        }

        Worker worker = get(columnNames, columnValues);
        DatabaseOperation.deleteItem(worker);
    }

    @Override
    public String show() {
        return DatabaseOperation.readAllItems("worker");

    }
}