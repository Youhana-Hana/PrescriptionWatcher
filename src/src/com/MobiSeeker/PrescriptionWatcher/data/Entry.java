package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;

import com.MobiSeeker.PrescriptionWatcher.R;
import com.google.gson.Gson;

import java.io.Serializable;
import java.sql.Time;

import java.util.Date;

public class Entry implements Serializable{

    public Entry(Context context, String medicineName,
                 Date startDate, Date endDate,
                 Time startTime, Time endTime,
                 double dosage, int timesPerDay, String comment,String prescriptionType,String prescriptionUser_imei, String prescriptionImagePath) {

        this.medicineName = medicineName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dosage = dosage;
        this.timesPerDay = timesPerDay;
        this.comment = comment;
        this.prescriptionType=prescriptionType;
        this.prescriptionUser=prescriptionUser_imei;
        this.prescriptionImagePath = prescriptionImagePath;
        this.validate(context);
        
    }

    private void validate(Context context) {
    	
        if (this.prescriptionType == null) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionMedicineNameError));
        }

        if (this.prescriptionType == null) {
            throw new UnsupportedOperationException(context.getString(R.string.prescriptionMedicineNameError));
        }

    	
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

        if (this.startTime.after(this.endTime)) {
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

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {
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

    public String getPrescriptionType() {
		return prescriptionType;
	}

	public void setPrescriptionType(String prescriptionType) {
		this.prescriptionType = prescriptionType;
	}

	public String getPrescriptionUser() {
		return prescriptionUser;
	}

    public String getPrescriptionImagePath() {
        return this.prescriptionImagePath;
    }

	public void setPrescriptionUser(String prescriptionUser) {
		this.prescriptionUser = prescriptionUser;
	}

    public void setPrescriptionImagePath(String prescriptionImagePath) {
        this.prescriptionImagePath = prescriptionImagePath;
    }



	private String medicineName;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private double dosage;
    private int timesPerDay;
    private String comment;
    private String prescriptionType;
    private String prescriptionUser;
    private String prescriptionImagePath;
    
    @Override
    public String toString() {
	// TODO Auto-generated method stub
    	return new Gson().toJson(this);
    }   
    
}
