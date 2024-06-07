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
            System.out.println("6. Marquer l'arrivée");
            System.out.println("7. Prolonger une réservation");
<<<<<<< HEAD
            System.out.println("8. Modifier une réservation");
            System.out.println("9. Quitter");
=======
            System.out.println("8. Quitter");
>>>>>>> f670ef088bc48d8cbdc0ea93e49d2f7305c0d968
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
                    reservationController.markArrival();
                    break;
                case 7:
                    reservationController.extendReservation();
                    break;
                case 8:
<<<<<<< HEAD
                    reservationController.modifyReservation();
                    break;
                case 9:
=======
>>>>>>> f670ef088bc48d8cbdc0ea93e49d2f7305c0d968
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
