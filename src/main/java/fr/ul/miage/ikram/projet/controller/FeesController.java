package fr.ul.miage.ikram.projet.controller;

import fr.ul.miage.ikram.projet.model.Fees;
import fr.ul.miage.ikram.projet.service.FeesService;
import fr.ul.miage.ikram.projet.util.InputHandler;
import fr.ul.miage.ikram.projet.util.OutputHandler;

import java.util.List;

public class FeesController {
    private final FeesService feesService;

    public FeesController() {
        this.feesService = new FeesService();
    }

    public void updateFeeAmount() {
        try {
            OutputHandler.printInfo("Liste des frais actuels :");

            List<Fees> allFees = feesService.getAllFees();
            for (Fees fee : allFees) {
                OutputHandler.printInfo(fee.toString());
            }

            String feeName = InputHandler.getString("Nom du frais à modifier : ");
            Fees fee = feesService.getFeesByName(feeName);

            if (fee == null) {
                OutputHandler.printError("Frais non trouvé.");
                return;
            }

            double newAmount = InputHandler.getDouble("Nouveau montant : ");
            fee.setAmount(newAmount);
            feesService.updateFees(fee);
            OutputHandler.printSuccess("Le montant du frais a été mis à jour avec succès.");
        } catch (Exception e) {
            OutputHandler.printError("Une erreur s'est produite lors de la mise à jour du frais : " + e.getMessage());
        }
    }
}
