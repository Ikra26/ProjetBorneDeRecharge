package fr.ul.miage.ikram.projet.dao;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;
import fr.ul.miage.ikram.projet.model.Reservation;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    private final Connection connection;

    public ReservationDAO() throws SQLException {
        this.connection = DatabaseConfig.getConnection(true);
    }

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    public void createReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservations (id, user_id, station_id, plate_number, start_time, end_time, is_guaranteed, is_completed, is_arrived, is_paid, extension_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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
            preparedStatement.setInt(11, reservation.getExtensionCount());

            preparedStatement.executeUpdate();
        }
    }

    public Reservation getReservationById(String id) throws SQLException {
        String query = "SELECT * FROM reservations WHERE id = ?";
        return getReservationDetails(query, id);
    }

    public List<Reservation> getReservationsByUserIdAndMonth(String userId, int month, int year) throws SQLException {
        String query = "SELECT * FROM reservations WHERE user_id = ? AND MONTH(start_time) = ? AND YEAR(start_time) = ?";
        List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, month);
            preparedStatement.setInt(3, year);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    reservations.add(extractReservationFromResultSet(resultSet));
                }
            }
        }

        return reservations;
    }

    public List<Reservation> getAllReservations() throws SQLException {
        String query = "SELECT * FROM reservations";
        List<Reservation> reservations = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                reservations.add(extractReservationFromResultSet(resultSet));
            }
        }

        return reservations;
    }

    public void updateReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE reservations SET user_id = ?, station_id = ?, plate_number = ?, start_time = ?, end_time = ?, is_guaranteed = ?, is_completed = ?, is_arrived = ?, is_paid = ?, extension_count = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reservation.getUserId());
            preparedStatement.setInt(2, reservation.getStationId());
            preparedStatement.setString(3, reservation.getLicensePlateNumber());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(reservation.getStartTime()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(reservation.getEndTime()));
            preparedStatement.setBoolean(6, reservation.isGuaranteed());
            preparedStatement.setBoolean(7, reservation.isCompleted());
            preparedStatement.setBoolean(8, reservation.isArrived());
            preparedStatement.setBoolean(9, reservation.isPaid());
            preparedStatement.setInt(10, reservation.getExtensionCount());
            preparedStatement.setString(11, reservation.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteReservation(String id) throws SQLException {
        String query = "DELETE FROM reservations WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
        }
    }

    private Reservation getReservationDetails(String query, String parameter) throws SQLException {
        Reservation reservation = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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
        reservation.setExtensionCount(resultSet.getInt("extension_count"));
        return reservation;
    }

    public boolean existsById(String id) throws SQLException {
        String query = "SELECT COUNT(*) FROM reservations WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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
