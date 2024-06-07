package fr.ul.miage.ikram.projet.service;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;
import fr.ul.miage.ikram.projet.dao.UserDAO;
import fr.ul.miage.ikram.projet.model.User;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        // Set up the connection to the H2 in-memory database
        connection = DatabaseConfig.getConnection(false);

        // Run the DDL to create the users table
        RunScript.execute(connection, new FileReader("src/test/resources/schema.sql"));

        // Initialize the UserService with a UserDAO that uses the H2 database
        userService = new UserService(new UserDAO(connection));
    }

    @After
    public void tearDown() throws SQLException {
        // Close the connection after each test
        connection.close();
    }

    @Test
    public void testRegisterUser() throws SQLException {
        User user = new User("12345678", "IKRAM", "IKRAM", "12 Rue de Rivoli", "0651234567", "Ikra26@example.com", "1234-5678-9012-3456", new ArrayList<>());
        userService.registerUser(user);

        User retrievedUser = userService.getUserById("12345678");
        assertNotNull(retrievedUser);
        assertEquals("IKRAM", retrievedUser.getFirstName());
        assertEquals("BOUGATAYA", retrievedUser.getLastName());
    }

    @Test
    public void testGetUserByMobileNumber() throws SQLException {
        User user = new User("12345678", "IKRAM", "IKRAM", "12 Rue de Rivoli", "0651234567", "Ikra26@example.com", "1234-5678-9012-3456", new ArrayList<>());
        userService.registerUser(user);

        User retrievedUser = userService.getUserByMobileNumber("0651234567");
        assertNotNull(retrievedUser);
        assertEquals("IKRAM", retrievedUser.getFirstName());
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        User user1 = new User("12345678", "IKRAM", "IKRAM", "12 Rue de Rivoli", "0651234567", "Ikra26@example.com", "1234-5678-9012-3456", new ArrayList<>());
        User user2 = new User("87654321", "SOUFIANE", "SOUFIANE", "34 Avenue des Champs-Élysées", "0678901234", "HachriSouf@example.com", "6543-2109-8765-4321", new ArrayList<>());
        userService.registerUser(user1);
        userService.registerUser(user2);

        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testUpdateUser() throws SQLException {
        User user = new User("12345678", "IKRAM", "IKRAM", "12 Rue de Rivoli", "0651234567", "Ikra26@example.com", "1234-5678-9012-3456", new ArrayList<>());
        userService.registerUser(user);

        user.setFirstName("Zakaria");
        userService.updateUser(user);

        User updatedUser = userService.getUserById("12345678");
        assertNotNull(updatedUser);
        assertEquals("Zakaria", updatedUser.getFirstName());
    }

    @Test
    public void testDeleteUser() throws SQLException {
        User user = new User("12345678", "IKRAM", "IKRAM", "12 Rue de Rivoli", "0651234567", "Ikra26@example.com", "1234-5678-9012-3456", new ArrayList<>());
        userService.registerUser(user);

        userService.deleteUser("12345678");

        User deletedUser = userService.getUserById("12345678");
        assertNull(deletedUser);
    }
}
