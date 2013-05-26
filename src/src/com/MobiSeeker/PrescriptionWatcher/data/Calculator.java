package com.MobiSeeker.PrescriptionWatcher.data;

import android.text.TextUtils;

import java.util.Date;

public class Calculator {

    public Schedule getSchedule(String medicineName,
                                Date startDate,
                                Date endDate,
                                int startTime,
                                int endTime,
                                double dosagePerDay) {

        this.validate(medicineName, startDate, endDate, startTime, endTime, dosagePerDay);

        return null;
    }

    private void validate(String medicineName, Date startDate, Date endDate, int startTime, int endTime, double dosagePerDay) {
        if (medicineName == null) {
            throw new UnsupportedOperationException("Invalid prescription medicine name.");
        }

        if (medicineName.isEmpty()){
            throw new UnsupportedOperationException("Invalid prescription medicine name.");
        }

        if (startDate.compareTo(endDate) > 0) {
            throw new UnsupportedOperationException("Invalid prescription end date. Should be after start date");
        }

        if (endTime < startTime) {
            throw new UnsupportedOperationException("Invalid prescription end time. Should be after start time");
        }

        if (dosagePerDay < 0) {
            throw new UnsupportedOperationException("Invalid prescription dosage. Should be greater than zero");
        }

        if (dosagePerDay == 0) {
            throw new UnsupportedOperationException("Invalid prescription dosage. Should be greater than zero");
        }
    }
}