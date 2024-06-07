package fr.ul.miage.ikram.projet.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private String id;
    private String userId;
    private int stationId;
    private String licensePlateNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isGuaranteed;
    private boolean isCompleted;
    private boolean isArrived;
    private boolean isPaid;
    private int extensionCount;

    // Constructors
    public Reservation() {}

    public Reservation(String id, String userId, int stationId, String licensePlateNumber, LocalDateTime startTime, LocalDateTime endTime, boolean isGuaranteed, boolean isCompleted, boolean isArrived, boolean isPaid, int extensionCount) {
        this.id = id;
        this.userId = userId;
        this.stationId = stationId;
        this.licensePlateNumber = licensePlateNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isGuaranteed = isGuaranteed;
        this.isCompleted = isCompleted;
        this.isArrived = isArrived;
        this.isPaid = isPaid;
        this.extensionCount = extensionCount;
    }

    // Getters and Setters
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

    public boolean isArrived() {
        return isArrived;
    }

    public void setArrived(boolean arrived) {
        isArrived = arrived;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getExtensionCount() {
        return extensionCount;
    }

    public void setExtensionCount(int extensionCount) {
        this.extensionCount = extensionCount;
    }

    public long getDuration() {
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    public boolean isPenaltyApplicable() {
        return !isArrived || !isCompleted;
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
                ", isArrived=" + isArrived +
                ", isPaid=" + isPaid +
                ", extensionCount=" + extensionCount +
                '}';
    }
}
