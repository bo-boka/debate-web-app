/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarah.debatewebapp.dto;

import java.util.Objects;

/**
 *
 * @author Sarah
 */
public class User {
    
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int wins;
    private int ties;
    private int totalDebates;
    private String role;
    private boolean enabled;
    
    public User(){
    }
    
    //rowmapper constructor
    public User(int id, String username, String passW, String firstN, String lastN, String email, int wins, int ties, int total, String role, boolean enabled){
        this.id = id;
        this.username = username;
        this.password = passW;
        this.firstName = firstN;
        this.lastName = lastN;
        this.email = email;
        this.wins = wins;
        this.ties = ties;
        this.totalDebates = total;
        this.role = role;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public int getTotalDebates() {
        return totalDebates;
    }

    public void setTotalDebates(int totalDebates) {
        this.totalDebates = totalDebates;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.lastName);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.role);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + this.wins;
        hash = 59 * hash + this.ties;
        hash = 59 * hash + this.totalDebates;
        hash = 59 * hash + (this.enabled ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.wins != other.wins) {
            return false;
        }
        if (this.ties != other.ties) {
            return false;
        }
        if (this.totalDebates != other.totalDebates) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
}

