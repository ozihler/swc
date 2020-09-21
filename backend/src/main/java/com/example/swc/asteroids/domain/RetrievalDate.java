package com.example.swc.asteroids.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RetrievalDate {
    private Date date;

    public RetrievalDate(String dateString) {
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            throw new IllegalRetrievalDateException("Could not parse date " + dateString);
        }
    }

    public Date toDate() {
        return date;
    }
}
