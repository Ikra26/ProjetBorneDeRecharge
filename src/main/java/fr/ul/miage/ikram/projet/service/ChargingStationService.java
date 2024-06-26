package fr.ul.miage.ikram.projet.service;

import fr.ul.miage.ikram.projet.dao.ChargingStationDAO;
import fr.ul.miage.ikram.projet.model.ChargingStation;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ChargingStationService {
    private final ChargingStationDAO chargingStationDAO;

    public ChargingStationService() throws SQLException {
        this.chargingStationDAO = new ChargingStationDAO();
    }

    public void addChargingStation(ChargingStation station) {
        try {
            chargingStationDAO.createChargingStation(station);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChargingStation getChargingStationById(int id) {
        try {
            return chargingStationDAO.getChargingStationById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChargingStation> getAllChargingStations() {
        try {
            return chargingStationDAO.getAllChargingStations();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChargingStation> getAvailableChargingStations(LocalDateTime startTime, int durationHours) {
        try {
            return chargingStationDAO.getAvailableChargingStations(startTime, durationHours);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void updateChargingStation(ChargingStation station) {
        try {
            chargingStationDAO.updateChargingStation(station);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChargingStation(int id) {
        try {
            chargingStationDAO.deleteChargingStation(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
