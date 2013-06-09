package com.MobiSeeker.PrescriptionWatcher.data;

import org.joda.time.LocalTime;

import java.util.Date;

public class Entry {

    public Entry(String medicineName,
                 Date startDate, Date endDate,
                 LocalTime startTime, LocalTime endTime,
                 double dosage, String comment) {
        this.medicineName = medicineName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dosage = dosage;
        this.comment = comment;
    }

    public String getMedicineName() {
        return this.medicineName;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public double getDosage() {
        return this.dosage;
    }

    public String getComment() {
        return this.comment;
    }

    private String medicineName;

    private Date startDate;
    private Date endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    private double dosage;
    private String comment;
}
