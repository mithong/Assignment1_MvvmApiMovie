package com.example.assignment1.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {

    @SuppressLint("SimpleDateFormat")
    public String formatDate(String dateUrl) {
        SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
        String date = null;
        try {
            Date formatDate = sdfFormat.parse(dateUrl);
            if (formatDate != null) {
                date = format.format(formatDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
