package org.tables;

import org.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Worker {
    private int worker_id;
    private String worker_name;
    private String worker_surname;
    private Integer worker_age;

    Connection connection;

    PreparedStatement preparedStatement;

    private final String[] columns = new String[]{"worker_id", "worker_name", "worker_surname", "worker_age"};
    String table_name = "worker";

    public Worker(List<String> columnNames, List<String> columnValues) {
        for (int i = 0; i < columnNames.size(); i++) {
            switch (columnNames.get(i)) {
                case "worker_id" -> this.worker_id = Integer.parseInt(columnValues.get(i));
                case "worker_name" -> this.worker_name = columnValues.get(i);
                case "worker_surname" -> this.worker_surname = columnValues.get(i);
                case "worker_age" -> this.worker_age = Integer.parseInt(columnValues.get(i));
                default -> throw new IllegalStateException("Unexpected value: " + columnNames.get(i));

            }
        }

    }

    public Worker() {
    }

    public void show() {
        try {
            String result = "";
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "select * from worker"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result += resultSet.getInt("worker_id") + " "
                        + resultSet.getString("worker_name") + " "
                        + resultSet.getString("worker_surname") + " "
                        + resultSet.getInt("worker_age") + "\n";
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
                    "insert into worker (worker_id, worker_name, worker_surname, worker_age) values (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, this.worker_id);
            preparedStatement.setString(2, this.worker_name);
            preparedStatement.setString(3, this.worker_surname);
            preparedStatement.setInt(4, this.worker_age);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update() {
        try {
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "update worker set worker_name = ?, worker_surname = ?, worker_age = ? where worker_id = ?"
            );
            preparedStatement.setString(1, this.worker_name);
            preparedStatement.setString(2, this.worker_surname);
            preparedStatement.setInt(3, this.worker_age);
            preparedStatement.setInt(4, this.worker_id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete() {
        try {
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "delete from worker where worker_id = ?"
            );
            preparedStatement.setInt(1, this.worker_id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
