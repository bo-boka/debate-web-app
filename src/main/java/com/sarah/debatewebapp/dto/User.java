/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarah.debatewebapp.dto;

import java.util.Objects;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Sarah
 */
public class User {
    
    private int id;
    @Size.List({
        @Size(max=21, message="Username cannot be more than {max} characters."),
        @Size(min=2, message="Username cannot be less than {min} characters.")
    })
    private String username;
    @Size.List({
        @Size(max=16, message="Password cannot be more than {max} characters."),
        @Size(min=8, message="Password cannot be less than {min} characters.")
    })
    private String password;
    @Size.List({
        @Size(max=25, message="First name cannot be more than {max} characters."),
        @Size(min=2, message="First name cannot be less than {min} characters.")
    })
    private String firstName;
    @Size.List({
        @Size(max=25, message="Last name cannot be more than {max} characters."),
        @Size(min=2, message="Last name cannot be less than {min} characters.")
    })
    private String lastName;
    @Size.List({
        @Size(max=30, message="Email cannot be more than {max} characters."),
        @Size(min=5, message="Username cannot be less than {min} characters.")
    })
    private String email;
    private int wins;
    private int ties;
    private int losses;
    private String role;
    private boolean enabled;
    
    public User(){
    }
    
    public User(String userN, String passW, String firstN, String lastN, String email, String role){
        this.username = userN;
        this.password = passW;
        this.firstName = firstN;
        this.lastName = lastN;
        this.email = email;
        this.role = role;
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
        this.losses = total;
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

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
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
        hash = 59 * hash + this.losses;
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
        if (this.losses != other.losses) {
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

