package com.example.bluejackkos;

public class Booking {
    private String bookingId;
    private String bookingDate;
    private String kosData;
    private String userId;

    public Booking(String bookingId, String bookingDate, String kosData, String userId) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.kosData = kosData;
        this.userId = userId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getKosData() {
        return kosData;
    }

    public void setKosData(String kosData) {
        this.kosData = kosData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
