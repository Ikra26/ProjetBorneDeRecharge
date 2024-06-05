package fr.ul.miage.ikram.projet.dao;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LicensePlateDAO {

    public void addLicensePlate(String userId, String plateNumber) throws SQLException {
        String query = "INSERT INTO license_plates (user_id, plate_number) VALUES (?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, plateNumber);

            preparedStatement.executeUpdate();
        }
    }

    public List<String> getLicensePlatesByUserId(String userId) throws SQLException {
        String query = "SELECT plate_number FROM license_plates WHERE user_id = ?";
        List<String> licensePlates = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    licensePlates.add(resultSet.getString("plate_number"));
                }
            }
        }

        return licensePlates;
    }

    public void deleteLicensePlate(String userId, String plateNumber) throws SQLException {
        String query = "DELETE FROM license_plates WHERE user_id = ? AND plate_number = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, plateNumber);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteLicensePlatesByUserId(String userId) throws SQLException {
        String query = "DELETE FROM license_plates WHERE user_id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);

            preparedStatement.executeUpdate();
        }
    }
}
