package fr.ul.miage.ikram.projet.controller;

import fr.ul.miage.ikram.projet.model.ChargingStation;
import fr.ul.miage.ikram.projet.model.Status;
import fr.ul.miage.ikram.projet.service.ChargingStationService;
import fr.ul.miage.ikram.projet.util.InputHandler;
import fr.ul.miage.ikram.projet.util.InputValidator;
import fr.ul.miage.ikram.projet.util.OutputHandler;

public class ChargingStationController {
    private final ChargingStationService chargingStationService;

    public ChargingStationController() {
        this.chargingStationService = new ChargingStationService();
    }

    public void createChargingStation() {
        try {
            OutputHandler.printInfo("Créer une nouvelle borne de recharge");

            String location = InputHandler.getString("Emplacement : ");
            String status = InputHandler.getString("Statut (AVAILABLE, UNAVAILABLE, RESERVED, OCCUPIED) : ");

            if (!InputValidator.isNotEmpty(location)) {
                OutputHandler.printError("L'emplacement doit être renseigné.");
                return;
            }

            if (!isValidStatus(status)) {
                OutputHandler.printError("Statut invalide. Veuillez entrer l'un des suivants : AVAILABLE, UNAVAILABLE, RESERVED, OCCUPIED.");
                return;
            }

            ChargingStation station = new ChargingStation();
            station.setLocation(location);
            station.setStatus(Status.valueOf(status));

            chargingStationService.addChargingStation(station);
            OutputHandler.printSuccess("Borne de recharge créée avec succès !");
        } catch (Exception e) {
            OutputHandler.printError("Une erreur s'est produite lors de la création de la borne de recharge : " + e.getMessage());
        }
    }

    private boolean isValidStatus(String status) {
        return status.equalsIgnoreCase("AVAILABLE") ||
                status.equalsIgnoreCase("UNAVAILABLE") ||
                status.equalsIgnoreCase("RESERVED") ||
                status.equalsIgnoreCase("OCCUPIED");
    }
}
