package fr.ul.miage.ikram.projet.dao;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;
import fr.ul.miage.ikram.projet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void createUser(User user) throws SQLException {
        String query = "INSERT INTO users (first_name, last_name, address, mobile_number, email, debit_card_number) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getMobileNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDebitCardNumber());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        }
    }

    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAddress(resultSet.getString("address"));
                user.setMobileNumber(resultSet.getString("mobile_number"));
                user.setEmail(resultSet.getString("email"));
                user.setDebitCardNumber(resultSet.getString("debit_card_number"));
                // License plate numbers should be fetched separately and set
            }
        }

        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAddress(resultSet.getString("address"));
                user.setMobileNumber(resultSet.getString("mobile_number"));
                user.setEmail(resultSet.getString("email"));
                user.setDebitCardNumber(resultSet.getString("debit_card_number"));
                // License plate numbers should be fetched separately and set
                users.add(user);
            }
        }

        return users;
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET first_name = ?, last_name = ?, address = ?, mobile_number = ?, email = ?, debit_card_number = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getMobileNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDebitCardNumber());
            preparedStatement.setInt(7, user.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }
}

