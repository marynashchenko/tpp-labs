package org.tables.profession;

import org.connection.DatabaseOperation;
import org.springframework.stereotype.Service;
import org.tables.common.BaseService;
import org.tables.worker.Worker;

import java.util.Arrays;
import java.util.List;

@Service
public class ProfessionService extends BaseService<Profession> {

    @Override
    public Profession get(List<String> columnNames, List<String> columnValues) {
        Profession profession = new Profession();
        for (int i = 0; i < columnNames.size(); i++) {
            switch (columnNames.get(i)) {
                case "profession_id" -> profession.setProfession_id(Integer.parseInt(columnValues.get(i)));
                case "profession_name" -> profession.setProfession_name(columnValues.get(i));
                case "profession_description" -> profession.setProfession_description(columnValues.get(i));
                case "workers_id" -> profession.setWorkers_id(Arrays.stream(columnValues.get(i).split(" "))
                        .map(
                                id -> {
                                    Worker worker = new Worker();
                                    worker.setWorker_id(Integer.parseInt(id));
                                    return worker;
                                }
                        )
                        .toArray(Worker[]::new));
                default -> throw new IllegalStateException("Unexpected value: " + columnNames.get(i));
            }
        }
        return profession;
    }

    @Override
    public void insert(List<String> columnNames, List<String> columnValues){
        if(columnNames.contains("profession_id")){
            throw new IllegalArgumentException("profession_id should not be provided for insert operation");
        }

        Profession profession = get(columnNames, columnValues);
        DatabaseOperation.insertItem(profession);
    }

    @Override
    public void update(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("profession_id")){
            throw new IllegalArgumentException("profession_id should be provided for update operation");
        }

        Profession profession = get(columnNames, columnValues);
        DatabaseOperation.updateItem(profession);
    }

    @Override
    public void delete(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("profession_id")){
            throw new IllegalArgumentException("profession_id should be provided for delete operation");
        }

        Profession profession = get(columnNames, columnValues);
        DatabaseOperation.deleteItem(profession);
    }

    @Override
    public String show(){
        return DatabaseOperation.readAllItems("Profession");
    }
}