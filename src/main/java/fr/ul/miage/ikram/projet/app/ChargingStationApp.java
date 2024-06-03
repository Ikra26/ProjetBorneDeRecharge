package fr.ul.miage.ikram.projet.app;


import fr.ul.miage.ikram.projet.controller.UserController;
import fr.ul.miage.ikram.projet.util.InputHandler;
import fr.ul.miage.ikram.projet.util.OutputHandler;

public class ChargingStationApp {
    private static final UserController userController = new UserController();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            OutputHandler.printInfo("Welcome to the Charging Station Management System");
            System.out.println("1. Register a new client");
            System.out.println("2. Add license plate to an existing client");
            System.out.println("3. Exit");
            int option = InputHandler.getInt("Please select an option: ");

            switch (option) {
                case 1:
                    userController.registerClient();
                    break;
                case 2:
                    userController.addLicensePlateToClient();
                    break;
                case 3:
                    exit = true;
                    OutputHandler.printInfo("Exiting the system...");
                    break;
                default:
                    OutputHandler.printWarning("Invalid option. Please try again.");
            }
        }

        InputHandler.closeScanner();
    }
}
