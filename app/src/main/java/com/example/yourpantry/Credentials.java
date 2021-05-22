package com.example.yourpantry;

public class Credentials {

    private String Username;
    private String Password;
    private String CnfPassword;

    public Credentials(String username, String password, String cnfPassword) {
        this.Username = username;
        this.Password = password;
        this.CnfPassword = cnfPassword;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCnfPassword() {
        return CnfPassword;
    }

    public void setCnfPassword(String cnfPassword) {
        CnfPassword = cnfPassword;
    }
}
