package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;

import com.MobiSeeker.PrescriptionWatcher.R;

import org.joda.time.LocalTime;

import java.util.Date;

public class Entry {

    public Entry(Context context, String medicineName,
                 Date startDate, Date endDate,
                 LocalTime startTime, LocalTime endTime,
                 double dosage, int timesPerDay, String comment) {

        this.medicineName = medicineName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dosage = dosage;
        this.timesPerDay = timesPerDay;
        this.comment = comment;

        this.validate(context);
    }

    private void validate(Context context) {
        if (this.medicineName == null) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionMedicineNameError));
        }

        if (this.medicineName.isEmpty()) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionMedicineNameError)
            );
        }

        if (this.startDate.after(endDate)) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionStartDateError));
        }

        if (this.startTime.isAfter(this.endTime)) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionStartTimeError));
        }

        if (this.dosage < 0) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionDosageError));
        }

        if (this.dosage == 0) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionDosageError));
        }

        if (this.timesPerDay < 0) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionTimesPerDayError));
        }

        if (this.timesPerDay == 0) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionTimesPerDayError));
        }
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

    public int getTimesPerDay() {
        return this.timesPerDay;
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
    private int timesPerDay;
    private String comment;
}
