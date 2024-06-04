package fr.ul.miage.ikram.projet.controller;

import fr.ul.miage.ikram.projet.model.User;
import fr.ul.miage.ikram.projet.service.UserService;
import fr.ul.miage.ikram.projet.util.InputHandler;
import fr.ul.miage.ikram.projet.util.InputValidator;
import fr.ul.miage.ikram.projet.util.OutputHandler;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public void registerClient() {
        try {
            OutputHandler.printInfo("Register a new client");

            String firstName = InputHandler.getString("First Name: ");
            String lastName = InputHandler.getString("Last Name: ");
            String address = InputHandler.getString("Address: ");
            String mobileNumber = InputHandler.getString("Mobile Number: ");
            String email = InputHandler.getString("Email: ");
            String debitCardNumber = InputHandler.getString("Debit Card Number: ");
            String licensePlate = InputHandler.getOptionalString("License Plate Number (optional, press Enter to skip): ");

            if (!InputValidator.isNotEmpty(firstName) || !InputValidator.isNotEmpty(lastName) || !InputValidator.isNotEmpty(address) || !InputValidator.isNotEmpty(debitCardNumber)) {
                OutputHandler.printError("All fields except license plate must be filled.");
                return;
            }

            if (!InputValidator.isValidPhoneNumber(mobileNumber)) {
                OutputHandler.printError("Invalid phone number. Please enter only digits.");
                return;
            }

            if (!InputValidator.isValidEmail(email)) {
                OutputHandler.printError("Invalid email format.");
                return;
            }

            List<String> licensePlates = new ArrayList<>();
            if (!licensePlate.isEmpty()) {
                licensePlates.add(licensePlate);
            }

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAddress(address);
            user.setMobileNumber(mobileNumber);
            user.setEmail(email);
            user.setDebitCardNumber(debitCardNumber);
            user.setLicensePlateNumbers(licensePlates);

            userService.registerUser(user);
            OutputHandler.printSuccess("Client registered successfully!");
        } catch (Exception e) {
            OutputHandler.printError("An error occurred while registering the client: " + e.getMessage());
        }
    }

    public void addLicensePlateToClient() {
        try {
            OutputHandler.printInfo("Add License Plate to an Existing Client");

            int clientId = InputHandler.getInt("Client ID: ");
            User user = userService.getUserById(clientId);
            if (user == null) {
                OutputHandler.printError("Client not found.");
                return;
            }

            String licensePlate = InputHandler.getString("License Plate Number: ");
            if (!InputValidator.isNotEmpty(licensePlate)) {
                OutputHandler.printError("License plate number cannot be empty.");
                return;
            }

            List<String> licensePlates = user.getLicensePlateNumbers();
            if (licensePlates == null) {
                licensePlates = new ArrayList<>();
            }
            licensePlates.add(licensePlate);
            user.setLicensePlateNumbers(licensePlates);

            userService.updateUser(user);
            OutputHandler.printSuccess("License plate added successfully!");
        } catch (Exception e) {
            OutputHandler.printError("An error occurred while adding the license plate: " + e.getMessage());
        }
    }
}
