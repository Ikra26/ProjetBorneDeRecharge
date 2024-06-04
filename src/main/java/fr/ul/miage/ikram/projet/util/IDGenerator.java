package fr.ul.miage.ikram.projet.util;

import java.security.SecureRandom;
import java.util.Random;

public class IDGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random random = new SecureRandom();

    public static String generateUniqueID(int idLength) {
        StringBuilder id = new StringBuilder(idLength);
        for (int i = 0; i < idLength; i++) {
            id.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return id.toString();
    }

    public static String generateReservationID() {
        StringBuilder reservationID = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (i > 0) {
                reservationID.append('-');
            }
            for (int j = 0; j < 5; j++) {
                reservationID.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
        }
        return reservationID.toString();
    }
}
