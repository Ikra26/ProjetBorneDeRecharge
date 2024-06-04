import fr.ul.miage.ikram.projet.util.InputHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {
    private final InputStream systemIn = System.in;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
    }

    @Test
    void testGetDateValid() {
        String input = "2024-06-01";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Date date = InputHandler.getDate("Enter date");
        assertNotNull(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("2024-06-01", sdf.format(date));
    }

    @Test
    void testGetDateInvalidThenValid() {
        String input = "invalid-date\n2024-06-01\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Date date = InputHandler.getDate("Enter date");
        assertNotNull(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("2024-06-01", sdf.format(date));
    }

    @Test
    void testGetIntValid() {
        String input = "42\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int number = InputHandler.getInt("Enter an integer: ");
        assertEquals(42, number);
    }

    @Test
    void testGetIntInvalidThenValid() {
        String input = "invalid\n42\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int number = InputHandler.getInt("Enter an integer: ");
        assertEquals(42, number);
    }
}
