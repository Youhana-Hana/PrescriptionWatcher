package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schedule {

    private ArrayList<Prescription> prescriptions = null;

    public Schedule() {
        this.prescriptions = new ArrayList<Prescription>();
    }

    public List<Prescription> getPrescriptions() {
        Collections.sort(this.prescriptions, new PrescriptionComparator());
        return Collections.unmodifiableList(this.prescriptions);
    }

    public void add(Prescription prescription) {
        this.prescriptions.add(prescription);
    }
 }
