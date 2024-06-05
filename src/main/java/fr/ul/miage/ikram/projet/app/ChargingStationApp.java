package fr.ul.miage.ikram.projet.app;

<<<<<<< HEAD

import fr.ul.miage.ikram.projet.controller.UserController;
=======
import fr.ul.miage.ikram.projet.controller.UserController;
import fr.ul.miage.ikram.projet.controller.ChargingStationController;
import fr.ul.miage.ikram.projet.controller.ReservationController;
>>>>>>> origin/main
import fr.ul.miage.ikram.projet.util.InputHandler;
import fr.ul.miage.ikram.projet.util.OutputHandler;

public class ChargingStationApp {
    private static final UserController userController = new UserController();
<<<<<<< HEAD
=======
    private static final ChargingStationController chargingStationController = new ChargingStationController();
    private static final ReservationController reservationController = new ReservationController();
>>>>>>> origin/main

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
<<<<<<< HEAD
            OutputHandler.printInfo("Welcome to the Charging Station Management System");
            System.out.println("1. Register a new client");
            System.out.println("2. Add license plate to an existing client");
            System.out.println("3. Exit");
            int option = InputHandler.getInt("Please select an option: ");
=======
            OutputHandler.printInfo("Bienvenue dans le système de gestion des bornes de recharge");
            System.out.println("1. Enregistrer un nouveau client");
            System.out.println("2. Ajouter une plaque d'immatriculation à un client existant");
            System.out.println("3. Créer une nouvelle borne de recharge");
            System.out.println("4. Planifier une réservation");
            System.out.println("5. Quitter");
            int option = InputHandler.getInt("Veuillez choisir une option : ");
>>>>>>> origin/main

            switch (option) {
                case 1:
                    userController.registerClient();
                    break;
                case 2:
                    userController.addLicensePlateToClient();
                    break;
                case 3:
<<<<<<< HEAD
                    exit = true;
                    OutputHandler.printInfo("Exiting the system...");
                    break;
                default:
                    OutputHandler.printWarning("Invalid option. Please try again.");
=======
                    chargingStationController.createChargingStation();
                    break;
                case 4:
                    reservationController.createReservation();
                    break;
                case 5:
                    exit = true;
                    OutputHandler.printInfo("Fermeture du système...");
                    break;
                default:
                    OutputHandler.printWarning("Option invalide. Veuillez réessayer.");
>>>>>>> origin/main
            }
        }

        InputHandler.closeScanner();
    }
}
