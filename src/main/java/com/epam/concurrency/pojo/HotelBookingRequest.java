package com.epam.concurrency.pojo;

import java.time.LocalDate;

public class HotelBookingRequest {
    private int id;
    private LocalDate date;
    private String hotel;

    public HotelBookingRequest(int id, LocalDate date, String hotel) {
        this.id = id;
        this.date = date;
        this.hotel = hotel;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "HotelBookingRequest{" +
                "id=" + id +
                ", date=" + date +
                ", hotel='" + hotel + '\'' +
                '}';
    }
}
