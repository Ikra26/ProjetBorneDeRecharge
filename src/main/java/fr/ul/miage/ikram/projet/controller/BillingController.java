package fr.ul.miage.ikram.projet.controller;

import fr.ul.miage.ikram.projet.model.User;
import fr.ul.miage.ikram.projet.service.BillingService;
import fr.ul.miage.ikram.projet.service.UserService;
import fr.ul.miage.ikram.projet.util.InputHandler;
import fr.ul.miage.ikram.projet.util.OutputHandler;

public class BillingController {
    private final BillingService billingService;
    private final UserService userService;

    public BillingController() {
        this.billingService = new BillingService();
        this.userService = new UserService();
    }

    public void generateMonthlyBill() {
        try {
            String userId = InputHandler.getString("Identifiant du client : ");
            User user = userService.getUserById(userId);
            if (user == null) {
                OutputHandler.printError("Client non trouvé.");
                return;
            }

            int month = InputHandler.getInt("Mois (1-12) : ");
            int year = InputHandler.getInt("Année : ");

            billingService.generateMonthlyBill(user, month, year);
        } catch (Exception e) {
            OutputHandler.printError("Une erreur s'est produite lors de la génération de la facture mensuelle : " + e.getMessage());
        }
    }
}
