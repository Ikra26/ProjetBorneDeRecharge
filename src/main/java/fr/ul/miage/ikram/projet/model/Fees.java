package fr.ul.miage.ikram.projet.model;

public class Fees {
    private int id;
    private String name;
    private double amount;

    // Constructors
    public Fees() {}

    public Fees(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
