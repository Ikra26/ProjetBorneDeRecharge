package fr.ul.miage.ikram.projet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int getInt(String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                OutputHandler.printError("Invalid input. Please enter a valid integer.");
            }
        }
        return number;
    }

    public static String getOptionalString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static boolean getBoolean(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt + " (y/n): ");
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("n")) {
                return input.equals("y");
            } else {
                OutputHandler.printError("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    public static Date getDate(String prompt) {
        Date date = null;
        while (true) {
            System.out.print(prompt + " (yyyy-MM-dd): ");
            String input = scanner.nextLine().trim();
            try {
                date = dateFormat.parse(input);
                break;
            } catch (ParseException e) {
                OutputHandler.printError("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
        return date;
    }

    public static void closeScanner() {
        scanner.close();
    }
}
