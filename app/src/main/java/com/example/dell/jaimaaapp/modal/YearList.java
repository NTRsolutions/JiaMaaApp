package com.example.dell.jaimaaapp.modal;

public class YearList {

   String year,session;
   Integer position;

    public YearList() {
    }

    public YearList(String year, String session, Integer position) {
        this.year = year;
        this.session = session;
        this.position = position;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
