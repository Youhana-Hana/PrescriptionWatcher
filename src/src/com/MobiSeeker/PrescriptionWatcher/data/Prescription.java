package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.Date;

public class Prescription {

    public Prescription(String medicineName, Date time, int dosage, String comment) {
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

    public int getDosage() {
        return this.dosage;
    }

    public String getComment() {
        return this.comment;
    }

    private String medicineName;

    private Date time;

    private int dosage;

    private String comment;
}
