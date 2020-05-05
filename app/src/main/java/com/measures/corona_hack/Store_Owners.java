package com.measures.corona_hack;

public class Store_Owners {
    private String username, store_name, timings, address, peak_time, off_day, city, locality, items, special_items, user_id;

    public Store_Owners(String username, String address, String email, String store_name, String timings, String peak_time, String off_day, String city, String locality, String items, String special_items) {
        this.username = username;
        this.address = address;
        this.store_name = store_name;
        this.timings = timings;
        this.peak_time = peak_time;
        this.off_day = off_day;
        this.city = city;
        this.locality = locality;
        this.items=items;
        this.special_items=special_items;
    }

    public Store_Owners(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getTimings() {
        return timings;
    }

    public String getPeak_time() {
        return peak_time;
    }

    public String getOff_day() {
        return off_day;
    }

    public String getCity() {
        return city;
    }

    public String getLocality() {
        return locality;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setPeak_time(String peak_time) {
        this.peak_time = peak_time;
    }

    public void setOff_day(String off_day) {
        this.off_day = off_day;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getSpecial_items() {
        return special_items;
    }

    public void setSpecial_items(String special_items) {
        this.special_items = special_items;
    }
}
