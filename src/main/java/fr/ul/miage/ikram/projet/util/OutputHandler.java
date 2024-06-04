package fr.ul.miage.ikram.projet.util;

public class OutputHandler {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";

    public static void printInfo(String message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void printWarning(String message) {
        System.out.println(YELLOW + message + RESET);
    }

    public static void printError(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void printSuccess(String message) {
        System.out.println(BLUE + message + RESET);
    }
}
