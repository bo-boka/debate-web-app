/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarah.debatewebapp.dto;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Sarah
 */
public class Rebuttal {
    
    private int id;
    private String content;
    private ArrayList<String> citedSources;    
    private String user;
    private String debate;
    private String type; //opening, refute, question, closing
    private String date;
    private boolean position;
    
    public Rebuttal(String content, String user, String debate, String type, String date, boolean position){
        this.content = content;
        this.user = user;
        this.debate = debate;
        this.type = type;
        this.date = date;
        this.position = position;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getCitedSources() {
        return citedSources;
    }

    public void setCitedSources(ArrayList<String> citedSources) {
        this.citedSources = citedSources;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDebate() {
        return debate;
    }

    public void setDebate(String debate) {
        this.debate = debate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPosition() {
        return position;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.content);
        hash = 89 * hash + Objects.hashCode(this.citedSources);
        hash = 89 * hash + Objects.hashCode(this.user);
        hash = 89 * hash + Objects.hashCode(this.debate);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.date);
        hash = 89 * hash + (this.position ? 1 : 0);
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
        final Rebuttal other = (Rebuttal) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.position != other.position) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.debate, other.debate)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.citedSources, other.citedSources)) {
            return false;
        }
        return true;
    }
    
    
}
