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
public class Debate {
    private int id;
    private String resolution;
    private String content;
    private ArrayList<String> citedSources;
    private String status; //statuses are 'intro', 'live', 'proWon', 'conWon', or 'wash' ..(or maybe 'expired'?)
    private String affirmativeUser;
    private String negativeUser;
    private int proVotes;
    private int conVotes;  
    private String category;
    private String date;
    private ArrayList<Rebuttal> rebuttals;
    private boolean published;
    
    public Debate(){
        
    }

    //ResultSetExtractor constructor
    public Debate(int id, String resolution, String content, String status, String affirmativeUser, String negativeUser, int proVotes, int conVotes, String category, String date, boolean published) {
        this.id = id;
        this.resolution = resolution;
        this.content = content;
        this.status = status;
        this.affirmativeUser = affirmativeUser;
        this.negativeUser = negativeUser;
        this.proVotes = proVotes;
        this.conVotes = conVotes;
        this.category = category;
        this.date = date;
        this.published = published;
    } 
    
    //new user debate constructor
    public Debate(String res, String content, String affUser, String cat, String date, boolean pub){
        this.resolution = res;
        this.content = content;
        this.affirmativeUser = affUser;
        this.category = cat;
        this.date = date;
        this.status = "intro";
        this.proVotes = 0;
        this.conVotes = 0;
        this.published = pub;
    }
    
    //test suite constructor
    public Debate(int id, String res, String content, String affUser, String cat, String date, boolean pub){
        this.id = id;
        this.resolution = res;
        this.content = content;
        this.affirmativeUser = affUser;
        this.category = cat;
        this.date = date;
        this.status = "intro";
        this.proVotes = 0;
        this.conVotes = 0;
        this.published = pub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAffirmativeUser() {
        return affirmativeUser;
    }

    public void setAffirmativeUser(String affirmativeUser) {
        this.affirmativeUser = affirmativeUser;
    }

    public String getNegativeUser() {
        return negativeUser;
    }

    public void setNegativeUser(String negativeUser) {
        this.negativeUser = negativeUser;
    }

    public int getProVotes() {
        return proVotes;
    }

    public void setProVotes(int proVotes) {
        this.proVotes = proVotes;
    }

    public int getConVotes() {
        return conVotes;
    }

    public void setConVotes(int conVotes) {
        this.conVotes = conVotes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Rebuttal> getRebuttals() {
        return rebuttals;
    }

    public void setRebuttals(ArrayList<Rebuttal> rebuttals) {
        this.rebuttals = rebuttals;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.resolution);
        hash = 47 * hash + Objects.hashCode(this.content);
        hash = 47 * hash + Objects.hashCode(this.citedSources);
        hash = 47 * hash + Objects.hashCode(this.status);
        hash = 47 * hash + Objects.hashCode(this.affirmativeUser);
        hash = 47 * hash + Objects.hashCode(this.negativeUser);
        hash = 47 * hash + this.proVotes;
        hash = 47 * hash + this.conVotes;
        hash = 47 * hash + Objects.hashCode(this.category);
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.rebuttals);
        hash = 47 * hash + (this.published ? 1 : 0);
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
        final Debate other = (Debate) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.proVotes != other.proVotes) {
            return false;
        }
        if (this.conVotes != other.conVotes) {
            return false;
        }
        if (this.published != other.published) {
            return false;
        }
        if (!Objects.equals(this.resolution, other.resolution)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.affirmativeUser, other.affirmativeUser)) {
            return false;
        }
        if (!Objects.equals(this.negativeUser, other.negativeUser)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.citedSources, other.citedSources)) {
            return false;
        }
        if (!Objects.equals(this.rebuttals, other.rebuttals)) {
            return false;
        }
        return true;
    }

    
    
}
