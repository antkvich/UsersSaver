package com.company;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String firstName;
    private String secondName;
    Role role = new Role();
    private String email;
    private Phone phone = new Phone();




    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    public List<String> getRole(){
        return role.roles;
    }

    public void setRole(){
        role.setRoles();
    }

    public void setPhone(){phone.setPhones();}

    public List<String> getPhone(){return phone.getPhones();}


}
