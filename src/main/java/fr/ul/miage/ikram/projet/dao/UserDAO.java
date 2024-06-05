package fr.ul.miage.ikram.projet.dao;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;
import fr.ul.miage.ikram.projet.model.User;
<<<<<<< HEAD
=======
import fr.ul.miage.ikram.projet.util.IDGenerator;
>>>>>>> origin/main

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
<<<<<<< HEAD

    public void createUser(User user) throws SQLException {
        String query = "INSERT INTO users (first_name, last_name, address, mobile_number, email, debit_card_number) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getMobileNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDebitCardNumber());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        }
    }

    public User getUserById(int id) throws SQLException {
=======
    private final LicensePlateDAO licensePlateDAO;

    public UserDAO() {
        this.licensePlateDAO = new LicensePlateDAO();
    }

    public void registerUser(User user) throws SQLException {
        try {
            String uniqueID;
            do {
                uniqueID = IDGenerator.generateUniqueID(8);
            } while (existsById(uniqueID));

            user.setId(uniqueID);
            createUser(user);

            // Add license plates
            for (String plate : user.getLicensePlateNumbers()) {
                licensePlateDAO.addLicensePlate(uniqueID, plate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }

    public void createUser(User user) throws SQLException {
        String query = "INSERT INTO users (id, first_name, last_name, address, mobile_number, email, debit_card_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getMobileNumber());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getDebitCardNumber());

            preparedStatement.executeUpdate();
        }
    }

    public User getUserById(String id) throws SQLException {
>>>>>>> origin/main
        String query = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

<<<<<<< HEAD
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAddress(resultSet.getString("address"));
                user.setMobileNumber(resultSet.getString("mobile_number"));
                user.setEmail(resultSet.getString("email"));
                user.setDebitCardNumber(resultSet.getString("debit_card_number"));
                // License plate numbers should be fetched separately and set
=======
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = mapResultSetToUser(resultSet);
                }
>>>>>>> origin/main
            }
        }

        return user;
    }

<<<<<<< HEAD
=======
    public User getUserByMobileNumber(String mobileNumber) throws SQLException {
        String query = "SELECT * FROM users WHERE mobile_number = ?";
        User user = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, mobileNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = mapResultSetToUser(resultSet);
                }
            }
        }

        return user;
    }

    public boolean existsById(String id) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
                return false;
            }
        }
    }

>>>>>>> origin/main
    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
<<<<<<< HEAD
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAddress(resultSet.getString("address"));
                user.setMobileNumber(resultSet.getString("mobile_number"));
                user.setEmail(resultSet.getString("email"));
                user.setDebitCardNumber(resultSet.getString("debit_card_number"));
                // License plate numbers should be fetched separately and set
=======
                User user = mapResultSetToUser(resultSet);
>>>>>>> origin/main
                users.add(user);
            }
        }

        return users;
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET first_name = ?, last_name = ?, address = ?, mobile_number = ?, email = ?, debit_card_number = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getMobileNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDebitCardNumber());
<<<<<<< HEAD
            preparedStatement.setInt(7, user.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
=======
            preparedStatement.setString(7, user.getId());

            preparedStatement.executeUpdate();

            // Update license plates
            licensePlateDAO.deleteLicensePlatesByUserId(user.getId());
            for (String plate : user.getLicensePlateNumbers()) {
                licensePlateDAO.addLicensePlate(user.getId(), plate);
            }
        }
    }

    public void deleteUser(String id) throws SQLException {
>>>>>>> origin/main
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

<<<<<<< HEAD
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }
}

=======
            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();

            // Delete license plates
            licensePlateDAO.deleteLicensePlatesByUserId(id);
        }
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setAddress(resultSet.getString("address"));
        user.setMobileNumber(resultSet.getString("mobile_number"));
        user.setEmail(resultSet.getString("email"));
        user.setDebitCardNumber(resultSet.getString("debit_card_number"));
        user.setLicensePlateNumbers(licensePlateDAO.getLicensePlatesByUserId(user.getId()));
        return user;
    }
}
>>>>>>> origin/main
