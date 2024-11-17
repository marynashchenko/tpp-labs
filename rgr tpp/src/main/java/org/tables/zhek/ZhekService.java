package org.tables.zhek;

import org.connection.DatabaseOperation;
import org.springframework.stereotype.Service;
import org.tables.common.BaseService;
import org.tables.profession.Profession;

import java.util.Arrays;
import java.util.List;

@Service
public class ZhekService extends BaseService<Zhek> {

    @Override
    public Zhek get(List<String> columnNames, List<String> columnValues) {
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

    @Override
    public void insert(List<String> columnNames, List<String> columnValues){
        if(columnNames.contains("zhek_id")){
            throw new IllegalArgumentException("zhek_id should not be provided for insert operation");
        }

        Zhek zhek = get(columnNames, columnValues);
        DatabaseOperation.insertItem(zhek);
    }

    @Override
    public void update(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("zhek_id")){
            throw new IllegalArgumentException("zhek_id should be provided for update operation");
        }

        Zhek zhek = get(columnNames, columnValues);
        DatabaseOperation.updateItem(zhek);
    }

    @Override
    public void delete(List<String> columnNames, List<String> columnValues){
        if(!columnNames.contains("zhek_id")){
            throw new IllegalArgumentException("zhek_id should be provided for delete operation");
        }

        Zhek zhek = get(columnNames, columnValues);
        DatabaseOperation.deleteItem(zhek);
    }

    @Override
    public String show() {
        return DatabaseOperation.readAllItems("zhek");

    }
}