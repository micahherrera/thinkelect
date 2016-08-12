package com.thinkelect.thinkelect;

/**
 * Created by micahherrera on 8/11/16.
 */
public class UserProfile {
    String username;
    String district;
    String state;
    String address;

    public UserProfile(String username, String district, String state, String address) {
        this.username = username;
        this.district = district;
        this.state = state;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
