package fr.ul.miage.ikram.projet.service;

import fr.ul.miage.ikram.projet.model.Fees;
import fr.ul.miage.ikram.projet.model.Reservation;
import fr.ul.miage.ikram.projet.model.User;
import fr.ul.miage.ikram.projet.util.OutputHandler;

import java.sql.SQLException;
import java.util.List;

public class BillingService {
    private final FeesService feesService;
    private final ReservationService reservationService;

    public BillingService() throws SQLException {
        this.feesService = new FeesService();
        this.reservationService = new ReservationService();
    }

    public void generateMonthlyBill(User user, int month, int year) {
        List<Reservation> reservations = reservationService.getReservationsByUserIdAndMonth(user.getId(), month, year);
        double totalChargingFees = 0.0;
        double totalReservationFees = 0.0;
        double totalPenaltyFees = 0.0;

        Fees chargingFee = feesService.getFeesByName("charging_fee");
        Fees reservationFee = feesService.getFeesByName("reservation_fee");
        Fees penaltyFee = feesService.getFeesByName("penalty_fee");

        for (Reservation reservation : reservations) {
            totalChargingFees += reservation.getDuration() * chargingFee.getAmount();
            totalReservationFees += reservationFee.getAmount();
            if (reservation.isPenaltyApplicable()) {
                totalPenaltyFees += penaltyFee.getAmount();
            }
        }

        double totalAmount = totalChargingFees + totalReservationFees + totalPenaltyFees;

        OutputHandler.printInfo("Facture mensuelle pour " + user.getFirstName() + " " + user.getLastName() + " (" + month + "/" + year + ")");
        OutputHandler.printInfo("Frais de recharge totaux: " + totalChargingFees);
        OutputHandler.printInfo("Frais de réservation totaux: " + totalReservationFees);
        OutputHandler.printInfo("Frais de pénalité totaux: " + totalPenaltyFees);
        OutputHandler.printSuccess("Montant total: " + totalAmount);
    }
}
