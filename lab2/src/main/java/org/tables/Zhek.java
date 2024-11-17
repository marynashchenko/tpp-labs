package org.tables;

import org.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Zhek {

    private int zhek_id;

    private String zhek_district;

    private String zhek_address;

    private Integer[] professions_id;

    private final String[] columns = new String[]{"zhek_id", "zhek_district", "zhek_address", "professions_id"};

    String table_name = "zhek";

    Connection connection;

    PreparedStatement preparedStatement;

    public Zhek(List<String> columnNames, List<String> columnValues) {
        for (int i = 0; i < columnNames.size(); i++) {
            switch (columnNames.get(i)) {
                case "zhek_id" -> this.zhek_id = Integer.parseInt(columnValues.get(i));
                case "zhek_district" -> this.zhek_district = columnValues.get(i);
                case "zhek_address" -> this.zhek_address = columnValues.get(i);
                case "professions_id" -> this.professions_id = Arrays.stream(columnValues.get(i).split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                default -> throw new IllegalStateException("Unexpected value: " + columnNames.get(i));
            }
        }
    }

    public Zhek() {
    }

    public void show() {
        try {
            String result = "";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "select * from zhek"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result += resultSet.getInt("zhek_id") + " "
                        + resultSet.getString("zhek_district") + " "
                        + resultSet.getString("zhek_address") + " "
                        + resultSet.getArray("professions_id") + "\n";
            }
            System.out.println(result);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert() {
        try {
            Connection connection;
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "insert into zhek (zhek_id, zhek_district, zhek_address, professions_id) values (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, this.zhek_id);
            preparedStatement.setString(2, this.zhek_district);
            preparedStatement.setString(3, this.zhek_address);
            preparedStatement.setArray(4, connection.createArrayOf("integer", this.professions_id));
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update() {
        try {
            Connection connection;
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "update zhek set zhek_district = ?, zhek_address = ?, professions_id = ? where zhek_id = ?"
            );
            preparedStatement.setString(1, this.zhek_district);
            preparedStatement.setString(2, this.zhek_address);
            preparedStatement.setArray(3, connection.createArrayOf("integer", this.professions_id));
            preparedStatement.setInt(4, this.zhek_id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete() {
        try {
            Connection connection;
            connection = DatabaseConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "delete from zhek where zhek_id = ?"
            );
            preparedStatement.setInt(1, this.zhek_id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
