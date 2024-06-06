package fr.ul.miage.ikram.projet.model;

import java.util.List;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String mobileNumber;
    private String email;
    private String debitCardNumber;
    private List<String> licensePlateNumbers;

    // Constructors
    public User() {}

    public User(String id, String firstName, String lastName, String address, String mobileNumber, String email, String debitCardNumber, List<String> licensePlateNumbers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.debitCardNumber = debitCardNumber;
        this.licensePlateNumbers = licensePlateNumbers;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDebitCardNumber() {
        return debitCardNumber;
    }

    public void setDebitCardNumber(String debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
    }

    public List<String> getLicensePlateNumbers() {
        return licensePlateNumbers;
    }

    public void setLicensePlateNumbers(List<String> licensePlateNumbers) {
        this.licensePlateNumbers = licensePlateNumbers;
    }

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", debitCardNumber='" + debitCardNumber + '\'' +
                ", licensePlateNumbers=" + licensePlateNumbers +
                '}';
    }
}
