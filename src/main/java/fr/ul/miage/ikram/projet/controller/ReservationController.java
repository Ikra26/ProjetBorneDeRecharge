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
<<<<<<< HEAD
=======
//                return;
>>>>>>> f670ef088bc48d8cbdc0ea93e49d2f7305c0d968
            }

            LocalDateTime startTime = LocalDateTime.now();

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
            reservation.setPaid(false);
            reservation.setArrived(false);
<<<<<<< HEAD
            reservation.setExtensionCount(0);
=======
>>>>>>> f670ef088bc48d8cbdc0ea93e49d2f7305c0d968

            reservationService.createReservation(reservation);
            OutputHandler.printSuccess("Réservation créée avec succès ! Numéro de réservation : " + reservation.getId());
        } catch (Exception e) {
            OutputHandler.printError("Une erreur s'est produite lors de la création de la réservation : " + e.getMessage());
        }
    }

    public void createUrgentReservation() {
        try {
            OutputHandler.printInfo("Créer une réservation urgente");

            String licensePlateNumber = InputHandler.getString("Numéro de plaque d'immatriculation : ");
            String userIdentifier = InputHandler.getString("Identifiant du client ou numéro de téléphone : ");
            User user = getUserByIdentifier(userIdentifier);

            if (user == null) {
                OutputHandler.printError("Client non trouvé.");
                return;
            }

            if (!user.getLicensePlateNumbers().contains(licensePlateNumber)) {
                OutputHandler.printError("Numéro de plaque d'immatriculation non trouvé pour ce client.");
<<<<<<< HEAD
=======
//                return;
>>>>>>> f670ef088bc48d8cbdc0ea93e49d2f7305c0d968
            }

            LocalDateTime startTime = LocalDateTime.now();
            int duration = InputHandler.getInt("Durée prévue de recharge en heures : ");
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
            reservation.setPaid(false);
            reservation.setArrived(false);
<<<<<<< HEAD
            reservation.setExtensionCount(0);
=======
>>>>>>> f670ef088bc48d8cbdc0ea93e49d2f7305c0d968

            reservationService.createReservation(reservation);
            OutputHandler.printSuccess("Réservation urgente créée avec succès ! Numéro de réservation : " + reservation.getId());
        } catch (Exception e) {
            OutputHandler.printError("Une erreur s'est produite lors de la création de la réservation urgente : " + e.getMessage());
        }
    }

    public void markArrival() {
        try {
            String reservationId = InputHandler.getString("Numéro de réservation : ");
            Reservation reservation = reservationService.getReservationById(reservationId);

            if (reservation == null) {
                OutputHandler.printError("Réservation non trouvée.");
                return;
            }

            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime startTime = reservation.getStartTime();
            LocalDateTime endTime = reservation.getEndTime();

            // Vérifier si le client arrive dans les 10 minutes après l'heure de début
            if (currentTime.isBefore(startTime.plusMinutes(10))) {
                reservation.setArrived(true);
                reservationService.updateReservation(reservation);
                OutputHandler.printSuccess("Arrivée marquée avec succès. Vous pouvez utiliser la borne.");
            } else {
                OutputHandler.printError("Vous êtes arrivé au-delà de la période d'attente.");
            }
        } catch (Exception e) {
            OutputHandler.printError("Une erreur s'est produite lors du marquage de l'arrivée : " + e.getMessage());
        }
    }

    public void extendReservation() {
        try {
            String reservationId = InputHandler.getString("Numéro de réservation : ");
            Reservation reservation = reservationService.getReservationById(reservationId);

            if (reservation == null) {
                OutputHandler.printError("Réservation non trouvée.");
                return;
            }

<<<<<<< HEAD
            if (reservation.getExtensionCount() >= 3) {
                OutputHandler.printError("La réservation ne peut pas être prolongée plus de 3 fois.");
                return;
            }

=======
>>>>>>> f670ef088bc48d8cbdc0ea93e49d2f7305c0d968
            int additionalHours = InputHandler.getInt("Durée supplémentaire en heures : ");
            LocalDateTime newEndTime = reservation.getEndTime().plusHours(additionalHours);

            List<ChargingStation> availableStations = chargingStationService.getAvailableChargingStations(reservation.getEndTime(), additionalHours);
            if (availableStations.isEmpty()) {
                OutputHandler.printError("Aucune borne disponible pour prolonger la réservation.");
                return;
            }

            reservation.setEndTime(newEndTime);
<<<<<<< HEAD
            reservation.setExtensionCount(reservation.getExtensionCount() + 1);
=======
>>>>>>> f670ef088bc48d8cbdc0ea93e49d2f7305c0d968
            reservationService.updateReservation(reservation);
            OutputHandler.printSuccess("Réservation prolongée avec succès.");
        } catch (Exception e) {
            OutputHandler.printError("Une erreur s'est produite lors de la prolongation de la réservation : " + e.getMessage());
        }
    }

<<<<<<< HEAD
    public void modifyReservation() {
        try {
            String reservationId = InputHandler.getString("Numéro de réservation : ");
            Reservation reservation = reservationService.getReservationById(reservationId);

            if (reservation == null) {
                OutputHandler.printError("Réservation non trouvée.");
                return;
            }

            LocalDateTime currentTime = LocalDateTime.now();
            if (currentTime.isAfter(reservation.getStartTime())) {
                OutputHandler.printError("Vous ne pouvez pas modifier une réservation après son heure de début.");
                return;
            }

            int newDuration = InputHandler.getInt("Nouvelle durée de la réservation en heures : ");
            LocalDateTime newStartTime = InputHandler.getDateTime("Nouvelle date de début de la réservation (yyyy-MM-dd HH:mm) : ");
            LocalDateTime newEndTime = newStartTime.plusHours(newDuration);

            List<ChargingStation> availableStations = chargingStationService.getAvailableChargingStations(newStartTime, newDuration);
            if (availableStations.isEmpty()) {
                OutputHandler.printError("Aucune borne disponible pour le nouvel intervalle.");
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

            reservation.setStartTime(newStartTime);
            reservation.setEndTime(newEndTime);
            reservation.setStationId(stationId);
            reservationService.updateReservation(reservation);
            OutputHandler.printSuccess("Réservation modifiée avec succès.");
        } catch (Exception e) {
            OutputHandler.printError("Une erreur s'est produite lors de la modification de la réservation : " + e.getMessage());
        }
    }
=======
>>>>>>> f670ef088bc48d8cbdc0ea93e49d2f7305c0d968

    private User getUserByIdentifier(String identifier) {
        User user = userService.getUserById(identifier);
        if (user == null) {
            user = userService.getUserByMobileNumber(identifier);
        }
        return user;
    }
}
