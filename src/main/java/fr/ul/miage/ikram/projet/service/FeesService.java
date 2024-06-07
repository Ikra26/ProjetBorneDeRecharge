package fr.ul.miage.ikram.projet.service;

import fr.ul.miage.ikram.projet.dao.FeesDAO;
import fr.ul.miage.ikram.projet.model.Fees;

import java.sql.SQLException;
import java.util.List;

public class FeesService {
    private final FeesDAO feesDAO;

    public FeesService() throws SQLException {
        this.feesDAO = new FeesDAO();
    }

    public Fees getFeesByName(String name) {
        try {
            return feesDAO.getFeesByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateFees(Fees fees) {
        try {
            feesDAO.updateFees(fees);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Fees> getAllFees() {
        try {
            return feesDAO.getAllFees();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
