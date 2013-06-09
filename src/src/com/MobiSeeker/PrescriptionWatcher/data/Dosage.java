package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.Date;

public class Dosage {

    public Dosage(String medicineName, Date time, double dosage, String comment) {
        this.medicineName = medicineName;
        this.time = time;
        this.dosage = dosage;
        this.comment = comment;
    }

    public String getMedicineName() {
        return this.medicineName;
    }

    public Date getTime() {
        return this.time;
    }

    public double getDosage() {
        return this.dosage;
    }

    public String getComment() {
        return this.comment;
    }

    private String medicineName;

    private Date time;

    private double dosage;

    private String comment;
}
