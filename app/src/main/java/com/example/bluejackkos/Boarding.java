package com.example.bluejackkos;

import android.os.Parcel;
import android.os.Parcelable;

public class Boarding implements Parcelable {
    private String id;
    private String name;
    private String facility;
    private int price;
    private String desc;
    private double longitude;
    private double latitude;
    private int imgId;

    public Boarding(String id, String name, String facility, int price, String desc, double longitude, double latitude, int imgId) {
        this.id = id;
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.desc = desc;
        this.longitude = longitude;
        this.latitude = latitude;
        this.imgId = imgId;
    }

    protected Boarding(Parcel in) {
        id = in.readString();
        name = in.readString();
        facility = in.readString();
        price = in.readInt();
        desc = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
        imgId = in.readInt();
    }

    public static final Creator<Boarding> CREATOR = new Creator<Boarding>() {
        @Override
        public Boarding createFromParcel(Parcel in) {
            return new Boarding(in);
        }

        @Override
        public Boarding[] newArray(int size) {
            return new Boarding[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(facility);
        dest.writeInt(price);
        dest.writeString(desc);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeInt(imgId);
    }
}
