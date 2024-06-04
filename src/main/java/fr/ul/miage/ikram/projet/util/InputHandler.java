package fr.ul.miage.ikram.projet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getString(String prompt) {
        try {
            System.out.print(prompt);
            return scanner.nextLine().trim();
        } catch (Exception e) {
            OutputHandler.printError("Error reading input: " + e.getMessage());
            return "";
        }
    }

    public static int getInt(String prompt) {
        int number;
        while (true) {
            try {
                System.out.print(prompt);
                number = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                OutputHandler.printError("Invalid input. Please enter a valid integer.");
            } catch (Exception e) {
                OutputHandler.printError("Error reading input: " + e.getMessage());
            }
        }
        return number;
    }

    public static String getOptionalString(String prompt) {
        try {
            System.out.print(prompt);
            return scanner.nextLine().trim();
        } catch (Exception e) {
            OutputHandler.printError("Error reading input: " + e.getMessage());
            return "";
        }
    }

    public static boolean getBoolean(String prompt) {
        String input;
        while (true) {
            try {
                System.out.print(prompt + " (y/n): ");
                input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("y") || input.equals("n")) {
                    return input.equals("y");
                } else {
                    OutputHandler.printError("Invalid input. Please enter 'y' or 'n'.");
                }
            } catch (Exception e) {
                OutputHandler.printError("Error reading input: " + e.getMessage());
            }
        }
    }

    public static Date getDate(String prompt) {
        Date date = null;
        while (true) {
            try {
                System.out.print(prompt + " (yyyy-MM-dd): ");
                String input = scanner.nextLine().trim();
                date = dateFormat.parse(input);
                break;
            } catch (ParseException e) {
                OutputHandler.printError("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            } catch (Exception e) {
                OutputHandler.printError("Error reading input: " + e.getMessage());
            }
        }
        return date;
    }

    public static void closeScanner() {
        try {
            scanner.close();
        } catch (Exception e) {
            OutputHandler.printError("Error closing scanner: " + e.getMessage());
        }
    }
}
