package fr.ul.miage.ikram.projet.service;

import fr.ul.miage.ikram.projet.dao.ReservationDAO;
import fr.ul.miage.ikram.projet.model.Reservation;

import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }

    public void createReservation(Reservation reservation) {
        try {
            reservationDAO.createReservation(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }

    public Reservation getReservationById(int id) {
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

    public void deleteReservation(int id) {
        try {
            reservationDAO.deleteReservation(id);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }
}

