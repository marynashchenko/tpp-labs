package org.tables.zhek;

import org.connection.DatabaseOperation;
import org.tables.profession.Profession;

import java.util.Arrays;
import java.util.List;

public class ZhekService {

    private Zhek getZhek(List<String> columnNames, List<String> columnValues) {
        Zhek zhek = new Zhek();
        for (int i = 0; i < columnNames.size(); i++) {
            switch (columnNames.get(i)) {
                case "zhek_id" -> zhek.setZhek_id(Integer.parseInt(columnValues.get(i)));
                case "zhek_district" -> zhek.setZhek_district(columnValues.get(i));
                case "zhek_address" -> zhek.setZhek_address(columnValues.get(i));
                case "professions_id" -> zhek.setProfessions_id(Arrays.stream(columnValues.get(i).split(" "))
                        .map(
                                id -> {
                                    Profession profession = new Profession();
                                    profession.setProfession_id(Integer.parseInt(id));
                                    return profession;
                                }
                        )
                        .toArray(Profession[]::new));
                default -> throw new IllegalStateException("Unexpected value: " + columnNames.get(i));
            }
        }
        return zhek;
    }

    public void insertZhek(List<String> columnNames, List<String> columnValues){
        if(columnNames.contains("zhek_id")){
            throw new IllegalArgumentException("zhek_id should not be provided for insert operation");
        }

        Zhek zhek = getZhek(columnNames, columnValues);
        DatabaseOperation.insertItem(zhek);
    }

    public void updateZhek(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("zhek_id")){
            throw new IllegalArgumentException("zhek_id should be provided for update operation");
        }

        Zhek zhek = getZhek(columnNames, columnValues);
        DatabaseOperation.updateItem(zhek);
    }

    public void deleteZhek(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("zhek_id")){
            throw new IllegalArgumentException("zhek_id should be provided for delete operation");
        }

        Zhek zhek = getZhek(columnNames, columnValues);
        DatabaseOperation.deleteItem(zhek);
    }
}