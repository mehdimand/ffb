package com.ffb.friendsfootbets.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Younes and Mehdi on 06/05/2017.
 */

public class User implements Serializable{
    private String name;
    private String username;
    private String email;
    private ArrayList<String> usersFollowed;
    private HashMap<String, String> bets;
    private ArrayList<String> tournamentsAccepted;
    private ArrayList<String> tournamentsInvited;
    private boolean profilePicture;

    public User() {
    }

    public User(String username) {
        this.username = username;
        this.bets = new HashMap<>();
    }

    public User(String name, String username, String email, boolean profilePicture) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
        this.bets = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getUsersFollowed() {
        return usersFollowed;
    }

    public void setUsersFollowed(ArrayList<String> usersFollowed) {
        this.usersFollowed = usersFollowed;
    }
    public void addUserFollowed(String username){
        this.usersFollowed.add(username);
    }

    public HashMap<String, String> getBets() {
        return bets;
    }

    public void setBets(HashMap<String, String> bets) {
        this.bets = bets;
    }

    public boolean hasProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(boolean profilePicture) {
        this.profilePicture = profilePicture;
    }

    public ArrayList<String> getTournamentsAccepted() {
        return tournamentsAccepted;
    }

    public void setTournamentsAccepted(ArrayList<String> tournamentsAccepted) {
        this.tournamentsAccepted = tournamentsAccepted;
    }

    public ArrayList<String> getTournamentsInvited() {
        return tournamentsInvited;
    }

    public void setTournamentsInvited(ArrayList<String> tournamentsInvited) {
        this.tournamentsInvited = tournamentsInvited;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", usersFollowed=" + usersFollowed +
                ", tournamentsAccepted=" + tournamentsAccepted +
                ", tournamentsInvited=" + tournamentsInvited +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
