package org.tables;

import org.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Profession {

    private int profession_id;

    private String profession_name;

    private String profession_description;

    private Integer[] worker_id;


    private final String[] columns = new String[]{"profession_id", "profession_name", "profession_description", "worker_id"};

    String table_name = "Profession";

    Connection connection;

    PreparedStatement preparedStatement;

    public Profession(List<String> columnNames, List<String> columnValues) {
        for (int i = 0; i < columnNames.size(); i++) {
            switch (columnNames.get(i)) {
                case "profession_id" -> this.profession_id = Integer.parseInt(columnValues.get(i));
                case "profession_name" -> this.profession_name = columnValues.get(i);
                case "profession_description" -> this.profession_description = columnValues.get(i);
                case "worker_id" -> this.worker_id = Arrays.stream(columnValues.get(i).split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                default -> throw new IllegalStateException("Unexpected value: " + columnNames.get(i));
            }
        }
    }

    public Profession() {
    }

    public void show() {
        try {
            String result = "";
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "select * from profession"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result += resultSet.getInt("profession_id") + " "
                        + resultSet.getString("profession_name") + " "
                        + resultSet.getString("profession_description") + " "
                        + resultSet.getArray("worker_id") + "\n";
            }
            System.out.println(result);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert() {
        try {
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "insert into profession (profession_id, profession_name, profession_description, worker_id) values (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, this.profession_id);
            preparedStatement.setString(2, this.profession_name);
            preparedStatement.setString(3, this.profession_description);
            preparedStatement.setArray(4, connection.createArrayOf("integer", this.worker_id));
            preparedStatement.executeUpdate();
            System.out.println("Insertion into table " + this.table_name + " is successful");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update() {
        try {
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "update profession set profession_name = ?, profession_description = ?, worker_id = ? where profession_id = ?"
            );
            preparedStatement.setString(1, this.profession_name);
            preparedStatement.setString(2, this.profession_description);
            preparedStatement.setArray(3, connection.createArrayOf("integer", this.worker_id));
            preparedStatement.setInt(4, this.profession_id);
            preparedStatement.executeUpdate();
            System.out.println("Update of table " + this.table_name + " is successful");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete() {
        try {
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "delete from profession where profession_id = ?"
            );
            preparedStatement.setInt(1, this.profession_id);
            preparedStatement.executeUpdate();
            System.out.println("Deletion from table " + this.table_name + " is successful");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}
