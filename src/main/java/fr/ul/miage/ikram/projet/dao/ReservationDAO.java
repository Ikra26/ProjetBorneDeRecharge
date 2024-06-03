package fr.ul.miage.ikram.projet.dao;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;
import fr.ul.miage.ikram.projet.model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public void createReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservations (user_id, station_id, plate_number, start_time, end_time, is_guaranteed, is_completed) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, reservation.getUserId());
            preparedStatement.setInt(2, reservation.getStationId());
            preparedStatement.setString(3, reservation.getLicensePlateNumber());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(reservation.getStartTime()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(reservation.getEndTime()));
            preparedStatement.setBoolean(6, reservation.isGuaranteed());
            preparedStatement.setBoolean(7, reservation.isCompleted());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.setId(generatedKeys.getInt(1));
            }
        }
    }

    public Reservation getReservationById(int id) throws SQLException {
        String query = "SELECT * FROM reservations WHERE id = ?";
        Reservation reservation = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                reservation = new Reservation();
                reservation.setId(resultSet.getInt("id"));
                reservation.setUserId(resultSet.getInt("user_id"));
                reservation.setStationId(resultSet.getInt("station_id"));
                reservation.setLicensePlateNumber(resultSet.getString("plate_number"));
                reservation.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
                reservation.setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime());
                reservation.setGuaranteed(resultSet.getBoolean("is_guaranteed"));
                reservation.setCompleted(resultSet.getBoolean("is_completed"));
            }
        }

        return reservation;
    }

    public List<Reservation> getAllReservations() throws SQLException {
        String query = "SELECT * FROM reservations";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("id"));
                reservation.setUserId(resultSet.getInt("user_id"));
                reservation.setStationId(resultSet.getInt("station_id"));
                reservation.setLicensePlateNumber(resultSet.getString("plate_number"));
                reservation.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
                reservation.setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime());
                reservation.setGuaranteed(resultSet.getBoolean("is_guaranteed"));
                reservation.setCompleted(resultSet.getBoolean("is_completed"));
                reservations.add(reservation);
            }
        }

        return reservations;
    }

    public void updateReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE reservations SET user_id = ?, station_id = ?, plate_number = ?, start_time = ?, end_time = ?, is_guaranteed = ?, is_completed = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservation.getUserId());
            preparedStatement.setInt(2, reservation.getStationId());
            preparedStatement.setString(3, reservation.getLicensePlateNumber());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(reservation.getStartTime()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(reservation.getEndTime()));
            preparedStatement.setBoolean(6, reservation.isGuaranteed());
            preparedStatement.setBoolean(7, reservation.isCompleted());
            preparedStatement.setInt(8, reservation.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteReservation(int id) throws SQLException {
        String query = "DELETE FROM reservations WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }
}

