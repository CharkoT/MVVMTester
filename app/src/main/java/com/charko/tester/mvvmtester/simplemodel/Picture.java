package com.charko.tester.mvvmtester.simplemodel;

import android.net.Uri;

public class Picture {

    private Uri uri;
    private String filename;
    private String Location;
    private double latitude;
    private double longitude;
    private String desc;

    public Picture(Uri uri, String filename, String location, double latitude, double longitude, String desc) {
        this.uri = uri;
        this.filename = filename;
        Location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.desc = desc;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
