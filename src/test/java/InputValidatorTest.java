import fr.ul.miage.ikram.projet.util.InputValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    void testIsValidPhoneNumber() {
        assertTrue(InputValidator.isValidPhoneNumber("1234567890"));
        assertFalse(InputValidator.isValidPhoneNumber("123-456-7890"));
        assertFalse(InputValidator.isValidPhoneNumber("abc123"));
        assertFalse(InputValidator.isValidPhoneNumber(null));
    }

    @Test
    void testIsValidEmail() {
        assertTrue(InputValidator.isValidEmail("test@example.com"));
        assertFalse(InputValidator.isValidEmail("test@.com"));
        assertFalse(InputValidator.isValidEmail("test@com"));
        assertFalse(InputValidator.isValidEmail("test.com"));
        assertFalse(InputValidator.isValidEmail(null));
    }

    @Test
    void testIsNotEmpty() {
        assertTrue(InputValidator.isNotEmpty("Non-empty string"));
        assertFalse(InputValidator.isNotEmpty(""));
        assertFalse(InputValidator.isNotEmpty("  "));
        assertFalse(InputValidator.isNotEmpty(null));
    }
}

