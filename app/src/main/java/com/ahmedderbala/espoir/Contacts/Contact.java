package com.ahmedderbala.espoir.Contacts;

public class Contact {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String status;
    private String espoirFunction;
    private String function;
    private String photo;

    public Contact(String firstName, String lastName, String phone, String email, String status, String espoirFunction, String function, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.espoirFunction = espoirFunction;
        this.function = function;
        this.function = photo;
    }

    public Contact(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEspoirFunction() {
        return espoirFunction;
    }

    public void setEspoirFunction(String espoirFunction) {
        this.espoirFunction = espoirFunction;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
