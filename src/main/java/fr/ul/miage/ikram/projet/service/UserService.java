package fr.ul.miage.ikram.projet.service;

import fr.ul.miage.ikram.projet.dao.UserDAO;
import fr.ul.miage.ikram.projet.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void registerUser(User user) {
        try {
<<<<<<< HEAD
            userDAO.createUser(user);
=======
            userDAO.registerUser(user);
>>>>>>> origin/main
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }

<<<<<<< HEAD
    public User getUserById(int id) {
=======
    public User getUserById(String id) {
>>>>>>> origin/main
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
<<<<<<< HEAD
            // Handle exception appropriately
=======
>>>>>>> origin/main
            return null;
        }
    }

<<<<<<< HEAD
=======
    public User getUserByMobileNumber(String mobileNumber) {
        try {
            return userDAO.getUserByMobileNumber(mobileNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


>>>>>>> origin/main
    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
<<<<<<< HEAD
            // Handle exception appropriately
=======
>>>>>>> origin/main
            return null;
        }
    }

    public void updateUser(User user) {
        try {
            userDAO.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
<<<<<<< HEAD
            // Handle exception appropriately
        }
    }

    public void deleteUser(int id) {
=======
        }
    }

    public void deleteUser(String id) {
>>>>>>> origin/main
        try {
            userDAO.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
<<<<<<< HEAD
            // Handle exception appropriately
=======
>>>>>>> origin/main
        }
    }
}
