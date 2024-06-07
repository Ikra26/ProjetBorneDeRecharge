package fr.ul.miage.ikram.projet.dao;

import fr.ul.miage.ikram.projet.config.DatabaseConfig;
import fr.ul.miage.ikram.projet.model.Fees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeesDAO {

    private final Connection connection;

    public FeesDAO() throws SQLException {
        this.connection = DatabaseConfig.getConnection(true);
    }

    public FeesDAO(Connection connection) {
        this.connection = connection;
    }

    public Fees getFeesByName(String name) throws SQLException {
        String query = "SELECT * FROM fees WHERE name = ?";
        Fees fees = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    fees = new Fees();
                    fees.setId(resultSet.getInt("id"));
                    fees.setName(resultSet.getString("name"));
                    fees.setAmount(resultSet.getDouble("amount"));
                }
            }
        }

        return fees;
    }

    public void updateFees(Fees fees) throws SQLException {
        String query = "UPDATE fees SET amount = ? WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, fees.getAmount());
            preparedStatement.setString(2, fees.getName());

            preparedStatement.executeUpdate();
        }
    }

    public List<Fees> getAllFees() throws SQLException {
        String query = "SELECT * FROM fees";
        List<Fees> feesList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Fees fees = new Fees();
                fees.setId(resultSet.getInt("id"));
                fees.setName(resultSet.getString("name"));
                fees.setAmount(resultSet.getDouble("amount"));
                feesList.add(fees);
            }
        }

        return feesList;
    }
}
