package fr.ul.miage.ikram.projet.model;

import java.time.LocalDateTime;

public class Reservation {
<<<<<<< HEAD
    private int id;
    private int userId;
=======
    private String id;
    private String userId;
>>>>>>> origin/main
    private int stationId;
    private String licensePlateNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isGuaranteed;
    private boolean isCompleted;

    // Constructors
    public Reservation() {}

<<<<<<< HEAD
    public Reservation(int id, int userId, int stationId, String licensePlateNumber, LocalDateTime startTime, LocalDateTime endTime, boolean isGuaranteed, boolean isCompleted) {
=======
    public Reservation(String id, String userId, int stationId, String licensePlateNumber, LocalDateTime startTime, LocalDateTime endTime, boolean isGuaranteed, boolean isCompleted) {
>>>>>>> origin/main
        this.id = id;
        this.userId = userId;
        this.stationId = stationId;
        this.licensePlateNumber = licensePlateNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isGuaranteed = isGuaranteed;
        this.isCompleted = isCompleted;
    }

    // Getters and Setters
<<<<<<< HEAD
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
=======
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
>>>>>>> origin/main
        this.userId = userId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isGuaranteed() {
        return isGuaranteed;
    }

    public void setGuaranteed(boolean isGuaranteed) {
        this.isGuaranteed = isGuaranteed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    // toString method
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", stationId=" + stationId +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isGuaranteed=" + isGuaranteed +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
