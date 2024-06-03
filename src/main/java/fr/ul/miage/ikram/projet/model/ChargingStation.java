package fr.ul.miage.ikram.projet.model;

public class ChargingStation {
    private int id;
    private String location;
    private Status status;

    // Constructors
    public ChargingStation() {}

    public ChargingStation(int id, String location, Status status) {
        this.id = id;
        this.location = location;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // toString method
    @Override
    public String toString() {
        return "ChargingStation{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
