package com.charko.tester.mvvmtester.simplemodel;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable {

    private String uri;
    private String filename;
    private String Location;
    private double latitude;
    private double longitude;
    private String desc;

    public Picture(String uri, String filename, String location, double latitude, double longitude, String desc) {
        this.uri = uri;
        this.filename = filename;
        Location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.desc = desc;
    }

    protected Picture(Parcel in) {
        uri = in.readString();
        filename = in.readString();
        Location = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        desc = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uri);
        parcel.writeString(filename);
        parcel.writeString(Location);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(desc);
    }
}
