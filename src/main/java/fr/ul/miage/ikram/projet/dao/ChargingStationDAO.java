package fr.ul.miage.ikram.projet.dao;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;
import fr.ul.miage.ikram.projet.model.ChargingStation;
import fr.ul.miage.ikram.projet.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChargingStationDAO {

    public void createChargingStation(ChargingStation station) throws SQLException {
        String query = "INSERT INTO charging_stations (location, status) VALUES (?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, station.getLocation());
            preparedStatement.setString(2, station.getStatus().name());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                station.setId(generatedKeys.getInt(1));
            }
        }
    }

    public ChargingStation getChargingStationById(int id) throws SQLException {
        String query = "SELECT * FROM charging_stations WHERE id = ?";
        ChargingStation station = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                station = new ChargingStation();
                station.setId(resultSet.getInt("id"));
                station.setLocation(resultSet.getString("location"));
                station.setStatus(Status.valueOf(resultSet.getString("status")));
            }
        }

        return station;
    }

    public List<ChargingStation> getAllChargingStations() throws SQLException {
        String query = "SELECT * FROM charging_stations";
        List<ChargingStation> stations = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                ChargingStation station = new ChargingStation();
                station.setId(resultSet.getInt("id"));
                station.setLocation(resultSet.getString("location"));
                station.setStatus(Status.valueOf(resultSet.getString("status")));
                stations.add(station);
            }
        }

        return stations;
    }

    public void updateChargingStation(ChargingStation station) throws SQLException {
        String query = "UPDATE charging_stations SET location = ?, status = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, station.getLocation());
            preparedStatement.setString(2, station.getStatus().name());
            preparedStatement.setInt(3, station.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteChargingStation(int id) throws SQLException {
        String query = "DELETE FROM charging_stations WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }
}

