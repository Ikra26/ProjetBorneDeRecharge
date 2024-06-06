package fr.ul.miage.ikram.projet.app;

import fr.ul.miage.ikram.projet.controller.UserController;
import fr.ul.miage.ikram.projet.controller.ChargingStationController;
import fr.ul.miage.ikram.projet.controller.ReservationController;
import fr.ul.miage.ikram.projet.util.InputHandler;
import fr.ul.miage.ikram.projet.util.OutputHandler;

public class ChargingStationApp {
    private static final UserController userController = new UserController();
    private static final ChargingStationController chargingStationController = new ChargingStationController();
    private static final ReservationController reservationController = new ReservationController();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            OutputHandler.printInfo("Bienvenue dans le système de gestion des bornes de recharge");
            System.out.println("1. Enregistrer un nouveau client");
            System.out.println("2. Ajouter une plaque d'immatriculation à un client existant");
            System.out.println("3. Créer une nouvelle borne de recharge");
            System.out.println("4. Planifier une réservation");
            System.out.println("5. Créer une réservation urgente");
            System.out.println("6. Quitter");
            int option = InputHandler.getInt("Veuillez choisir une option : ");

            switch (option) {
                case 1:
                    userController.registerClient();
                    break;
                case 2:
                    userController.addLicensePlateToClient();
                    break;
                case 3:
                    chargingStationController.createChargingStation();
                    break;
                case 4:
                    reservationController.createReservation();
                    break;
                case 5:
                    reservationController.createUrgentReservation();
                    break;
                case 6:
                    exit = true;
                    OutputHandler.printInfo("Fermeture du système...");
                    break;
                default:
                    OutputHandler.printWarning("Option invalide. Veuillez réessayer.");
            }
        }

        InputHandler.closeScanner();
    }
}
