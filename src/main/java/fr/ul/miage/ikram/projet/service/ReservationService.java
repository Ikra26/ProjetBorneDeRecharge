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
            // Handle exception appropriately
        }
    }

    public Reservation getReservationById(String id) {
        try {
            return reservationDAO.getReservationById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
            return null;
        }
    }

    public List<Reservation> getAllReservations() {
        try {
            return reservationDAO.getAllReservations();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
            return null;
        }
    }

    public void updateReservation(Reservation reservation) {
        try {
            reservationDAO.updateReservation(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }

    public void deleteReservation(String id) {
        try {
            reservationDAO.deleteReservation(id);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }
}
