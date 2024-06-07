package fr.ul.miage.ikram.projet.service;

import fr.ul.miage.ikram.projet.dao.UserDAO;
import fr.ul.miage.ikram.projet.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService() throws SQLException {
        this.userDAO = new UserDAO();
    }

    public UserService(UserDAO userDAO) throws SQLException {
        this.userDAO = userDAO;
    }

    public void registerUser(User user) {
        try {
            userDAO.registerUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(String id) {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByMobileNumber(String mobileNumber) {
        try {
            return userDAO.getUserByMobileNumber(mobileNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(User user) {
        try {
            userDAO.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String id) {
        try {
            userDAO.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
