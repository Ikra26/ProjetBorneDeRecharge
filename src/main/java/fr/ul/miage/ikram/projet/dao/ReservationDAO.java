package fr.ul.miage.ikram.projet.dao;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;
import fr.ul.miage.ikram.projet.model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public void createReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservations (id, user_id, station_id, plate_number, start_time, end_time, is_guaranteed, is_completed, is_arrived, is_paid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reservation.getId());
            preparedStatement.setString(2, reservation.getUserId());
            preparedStatement.setInt(3, reservation.getStationId());
            preparedStatement.setString(4, reservation.getLicensePlateNumber());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(reservation.getStartTime()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(reservation.getEndTime()));
            preparedStatement.setBoolean(7, reservation.isGuaranteed());
            preparedStatement.setBoolean(8, reservation.isCompleted());
            preparedStatement.setBoolean(9, reservation.isArrived());
            preparedStatement.setBoolean(10, reservation.isPaid());

            preparedStatement.executeUpdate();
        }
    }

    public Reservation getReservationById(String id) throws SQLException {
        String query = "SELECT * FROM reservations WHERE id = ?";
        return getReservationDetails(query, id);
    }

    public List<Reservation> getAllReservations() throws SQLException {
        String query = "SELECT * FROM reservations";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                reservations.add(extractReservationFromResultSet(resultSet));
            }
        }

        return reservations;
    }

    public void updateReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE reservations SET user_id = ?, station_id = ?, plate_number = ?, start_time = ?, end_time = ?, is_guaranteed = ?, is_completed = ?, is_arrived = ?, is_paid = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reservation.getUserId());
            preparedStatement.setInt(2, reservation.getStationId());
            preparedStatement.setString(3, reservation.getLicensePlateNumber());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(reservation.getStartTime()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(reservation.getEndTime()));
            preparedStatement.setBoolean(6, reservation.isGuaranteed());
            preparedStatement.setBoolean(7, reservation.isCompleted());
            preparedStatement.setBoolean(8, reservation.isArrived());
            preparedStatement.setBoolean(9, reservation.isPaid());
            preparedStatement.setString(10, reservation.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteReservation(String id) throws SQLException {
        String query = "DELETE FROM reservations WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
        }
    }

    private Reservation getReservationDetails(String query, String parameter) throws SQLException {
        Reservation reservation = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, parameter);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    reservation = extractReservationFromResultSet(resultSet);
                }
            }
        }

        return reservation;
    }

    private Reservation extractReservationFromResultSet(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getString("id"));
        reservation.setUserId(resultSet.getString("user_id"));
        reservation.setStationId(resultSet.getInt("station_id"));
        reservation.setLicensePlateNumber(resultSet.getString("plate_number"));
        reservation.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
        reservation.setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime());
        reservation.setGuaranteed(resultSet.getBoolean("is_guaranteed"));
        reservation.setCompleted(resultSet.getBoolean("is_completed"));
        reservation.setPaid(resultSet.getBoolean("is_paid"));
        reservation.setArrived(resultSet.getBoolean("is_arrived"));
        return reservation;
    }

    public boolean existsById(String id) throws SQLException {
        String query = "SELECT COUNT(*) FROM reservations WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
                return false;
            }
        }
    }
}
