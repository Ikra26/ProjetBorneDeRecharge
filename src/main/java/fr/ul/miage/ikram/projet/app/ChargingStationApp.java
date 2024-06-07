package fr.ul.miage.ikram.projet.app;

import fr.ul.miage.ikram.projet.controller.UserController;
import fr.ul.miage.ikram.projet.controller.ChargingStationController;
import fr.ul.miage.ikram.projet.controller.ReservationController;
import fr.ul.miage.ikram.projet.controller.BillingController;
import fr.ul.miage.ikram.projet.controller.FeesController;
import fr.ul.miage.ikram.projet.util.InputHandler;
import fr.ul.miage.ikram.projet.util.OutputHandler;

public class ChargingStationApp {
    private static final UserController userController = new UserController();
    private static final ChargingStationController chargingStationController = new ChargingStationController();
    private static final ReservationController reservationController = new ReservationController();
    private static final BillingController billingController = new BillingController();
    private static final FeesController feesController = new FeesController();

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
            System.out.println("8. Modifier une réservation");
            System.out.println("9. Générer une facture mensuelle");
            System.out.println("10. Mettre à jour un montant de frais");
            System.out.println("11. Quitter");
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
                    reservationController.modifyReservation();
                    break;
                case 9:
                    billingController.generateMonthlyBill();
                    break;
                case 10:
                    feesController.updateFeeAmount();
                    break;
                case 11:
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
