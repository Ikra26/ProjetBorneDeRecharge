package fr.ul.miage.ikram.projet.controller;

import fr.ul.miage.ikram.projet.model.ChargingStation;
import fr.ul.miage.ikram.projet.model.Reservation;
import fr.ul.miage.ikram.projet.model.User;
import fr.ul.miage.ikram.projet.service.ChargingStationService;
import fr.ul.miage.ikram.projet.service.ReservationService;
import fr.ul.miage.ikram.projet.service.UserService;
import fr.ul.miage.ikram.projet.util.InputHandler;
import fr.ul.miage.ikram.projet.util.OutputHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ReservationController {
    private final ReservationService reservationService;
    private final ChargingStationService chargingStationService;
    private final UserService userService;

    public ReservationController() {
        this.reservationService = new ReservationService();
        this.chargingStationService = new ChargingStationService();
        this.userService = new UserService();
    }

    public void createReservation() {
        try {
            OutputHandler.printInfo("Créer une nouvelle réservation");

            String userIdentifier = InputHandler.getString("Identifiant du client ou numéro de téléphone : ");
            User user = getUserByIdentifier(userIdentifier);
            if (user == null) {
                OutputHandler.printError("Client non trouvé.");
                return;
            }

            String licensePlateNumber = InputHandler.getString("Numéro de plaque d'immatriculation : ");
            if (!user.getLicensePlateNumbers().contains(licensePlateNumber)) {
                OutputHandler.printError("Numéro de plaque d'immatriculation non trouvé pour ce client.");
//                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String dateInput = InputHandler.getString("Date de la réservation (yyyy-MM-dd HH:mm) : ");
            LocalDateTime startTime = LocalDateTime.parse(dateInput, formatter);

            int duration = InputHandler.getInt("Durée de la réservation en heures : ");
            LocalDateTime endTime = startTime.plusHours(duration);

            List<ChargingStation> availableStations = chargingStationService.getAvailableChargingStations(startTime, duration);
            if (availableStations.isEmpty()) {
                OutputHandler.printError("Aucune borne disponible pour l'intervalle souhaité.");
                return;
            }

            OutputHandler.printInfo("Bornes disponibles : ");
            for (ChargingStation station : availableStations) {
                System.out.println("Borne ID: " + station.getId() + ", Emplacement: " + station.getLocation());
            }

            int stationId = InputHandler.getInt("Choisissez l'ID de la borne : ");
            Optional<ChargingStation> optionalStation = availableStations.stream().filter(station -> station.getId() == stationId).findFirst();
            if (!optionalStation.isPresent()) {
                OutputHandler.printError("Borne non trouvée.");
                return;
            }

            Reservation reservation = new Reservation();
            reservation.setUserId(user.getId());
            reservation.setStationId(stationId);
            reservation.setLicensePlateNumber(licensePlateNumber);
            reservation.setStartTime(startTime);
            reservation.setEndTime(endTime);
            reservation.setGuaranteed(true);
            reservation.setCompleted(false);

            reservationService.createReservation(reservation);
            OutputHandler.printSuccess("Réservation créée avec succès ! Numéro de réservation : " + reservation.getId());
        } catch (Exception e) {
            OutputHandler.printError("Une erreur s'est produite lors de la création de la réservation : " + e.getMessage());
        }
    }

    private User getUserByIdentifier(String identifier) {
        User user = userService.getUserById(identifier);
        if (user == null) {
            user = userService.getUserByMobileNumber(identifier);
        }
        return user;
    }
}
