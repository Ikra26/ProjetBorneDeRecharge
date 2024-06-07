package fr.ul.miage.ikram.projet.service;

import fr.ul.miage.ikram.projet.dao.ReservationDAO;
import fr.ul.miage.ikram.projet.model.Reservation;
import fr.ul.miage.ikram.projet.util.IDGenerator;

import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }

    public void createReservation(Reservation reservation) {
        try {
            String reservationId;
            do {
                reservationId = IDGenerator.generateReservationID();
            } while (reservationDAO.existsById(reservationId));

            reservation.setId(reservationId);
            reservationDAO.createReservation(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reservation getReservationById(String id) {
        try {
            return reservationDAO.getReservationById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Reservation> getReservationsByUserIdAndMonth(String userId, int month, int year) {
        try {
            return reservationDAO.getReservationsByUserIdAndMonth(userId, month, year);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Reservation> getAllReservations() {
        try {
            return reservationDAO.getAllReservations();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateReservation(Reservation reservation) {
        try {
            reservationDAO.updateReservation(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(String id) {
        try {
            reservationDAO.deleteReservation(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
