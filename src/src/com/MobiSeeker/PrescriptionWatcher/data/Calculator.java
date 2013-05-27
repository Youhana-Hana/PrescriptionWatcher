package com.MobiSeeker.PrescriptionWatcher.data;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Calculator {

    public Schedule getSchedule(String medicineName,
                                Date startDate,
                                Date endDate,
                                Time startTime,
                                Time endTime,
                                double dosage,
                                int  timesPerDay,
                                String comment) {

        this.validate(medicineName, startDate, endDate, startTime, endTime, dosage, timesPerDay);

        long days = this.daysBetween(startDate, endDate);

        long intervals = this.getInterval(startTime, endTime, timesPerDay);

        return this.fillPrescriptions(medicineName, dosage, startDate, startTime, timesPerDay, days, intervals, comment);
    }


    private Schedule fillPrescriptions(String medicineName,
                                       double dosage,
                                       Date startDate,
                                       Time startTime,
                                       long timesPerDay,
                                       long days,
                                       long intervals,
                                       String comment) {

        Schedule schedule = new Schedule();

        for (int day = 0; day < days; day ++)
        {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.set(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DATE),
                    0,
                    0,
                    0);

            calendar.add(Calendar.DATE, day);

            Calendar time = Calendar.getInstance();
            time.setTime(startTime);

            calendar.set(Calendar.AM_PM, Calendar.AM);
            calendar.set(Calendar.HOUR, time.get(Calendar.HOUR));
            calendar.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, time.get(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND, 0);

            for (int index = 0; index < timesPerDay; ++index) {
                if (index != 0) {
                    calendar.add(Calendar.MINUTE, (int)intervals);
                }

                Prescription prescription = new Prescription(medicineName, calendar.getTime(), dosage, comment);
                schedule.add(prescription);

                }
        }

        return schedule;
    }

    private void validate(String medicineName, Date startDate, Date endDate, Time startTime, Time endTime, double dosage, int timesPerDay) {
        if (medicineName == null) {
            throw new UnsupportedOperationException("Invalid prescription medicine name.");
        }

        if (medicineName.isEmpty()){
            throw new UnsupportedOperationException("Invalid prescription medicine name.");
        }

        if (startDate.after(endDate)) {
            throw new UnsupportedOperationException("Invalid prescription end date. Should be after start date");
        }

        if (startTime.after(endTime)) {
            throw new UnsupportedOperationException("Invalid prescription end time. Should be after start time");
        }

        if (dosage < 0) {
            throw new UnsupportedOperationException("Invalid prescription dosage. Should be greater than zero");
        }

        if (dosage == 0) {
            throw new UnsupportedOperationException("Invalid prescription dosage. Should be greater than zero");
        }

        if (timesPerDay < 0) {
            throw new UnsupportedOperationException("Invalid prescription times per day. Should be greater than zero");
        }

        if (timesPerDay == 0) {
            throw new UnsupportedOperationException("Invalid prescription times per day. Should be greater than zero");
        }
    }

    private long daysBetween(Date startDate, Date endDate) {
        long daysBetweenInMilliSeconds = endDate.getTime() - startDate.getTime();

        return Math.abs((daysBetweenInMilliSeconds/(1000*60*60*24))) + 1;
    }

    private long getInterval(Time startTime, Time endTime, double timesPerDay) {
        long periodInMilliSeconds = endTime.getTime() - startTime.getTime();
        long periodInMinutes = periodInMilliSeconds /(1000 * 60);
        long dosages = (long) Math.ceil(timesPerDay);

        return periodInMinutes / dosages;
    }

}