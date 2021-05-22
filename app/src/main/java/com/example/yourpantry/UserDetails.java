package com.example.yourpantry;

public class UserDetails {

    private String FirstName;
    private String LastName;


    public UserDetails(String firstName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }


}
