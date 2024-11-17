package org.tables.profession;

import org.connection.DatabaseOperation;
import org.tables.worker.Worker;

import java.util.Arrays;
import java.util.List;

public class ProfessionService {

    private Profession getProfession(List<String> columnNames, List<String> columnValues) {
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

    public void insertProfession(List<String> columnNames, List<String> columnValues){
        if(columnNames.contains("profession_id")){
            throw new IllegalArgumentException("profession_id should not be provided for insert operation");
        }

        Profession profession = getProfession(columnNames, columnValues);
        DatabaseOperation.insertItem(profession);
    }

    public void updateProfession(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("profession_id")){
            throw new IllegalArgumentException("profession_id should be provided for update operation");
        }

        Profession profession = getProfession(columnNames, columnValues);
        DatabaseOperation.updateItem(profession);
    }

    public void deleteProfession(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("profession_id")){
            throw new IllegalArgumentException("profession_id should be provided for delete operation");
        }

        Profession profession = getProfession(columnNames, columnValues);
        DatabaseOperation.deleteItem(profession);
    }
}