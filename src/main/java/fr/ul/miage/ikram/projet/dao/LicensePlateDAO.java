package fr.ul.miage.ikram.projet.dao;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LicensePlateDAO {

    private final Connection connection;

    public LicensePlateDAO() throws SQLException {
        this.connection = DatabaseConfig.getConnection(true);
    }

    public LicensePlateDAO(Connection connection) {
        this.connection = connection;
    }

    public void addLicensePlate(String userId, String plateNumber) throws SQLException {
        String query = "INSERT INTO license_plates (user_id, plate_number) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, plateNumber);

            preparedStatement.executeUpdate();
        }
    }

    public List<String> getLicensePlatesByUserId(String userId) throws SQLException {
        String query = "SELECT plate_number FROM license_plates WHERE user_id = ?";
        List<String> licensePlates = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, plateNumber);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteLicensePlatesByUserId(String userId) throws SQLException {
        String query = "DELETE FROM license_plates WHERE user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);

            preparedStatement.executeUpdate();
        }
    }
}
