package com.ad.platform.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    @NotEmpty
    private String firstName;
    private String middleName;
    @NotEmpty
    private String lastName;
    @Email
    private String emailId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;
    
    public User(){
        
    }

    public User(int id, String firstName, String middleName, String lastName, String emailId, String password,
            String city, String country, UserType userType) {
        this.setUserId(id);
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setEmailId(emailId);
        this.setCity(city);
        this.setCountry(country);
        this.setPassword(password);
        this.setUserType(userType);
    }
    
    public User(String firstName, String middleName, String lastName, String emailId, String password,
            String city, String country, UserType userType) {
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setEmailId(emailId);
        this.setCity(city);
        this.setCountry(country);
        this.setPassword(password);
        this.setUserType(userType);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
                + lastName + ", emailId=" + emailId + ", password=" + password + ", city=" + city + ", country="
                + country + ", userType=" + userType.toString() + "]";
    }
}
