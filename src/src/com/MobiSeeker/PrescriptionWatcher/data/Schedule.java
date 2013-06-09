package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schedule {

    private ArrayList<Dosage> prescriptions = null;

    public Schedule() {
        this.prescriptions = new ArrayList<Dosage>();
    }

    public List<Dosage> getPrescriptions() {
        Collections.sort(this.prescriptions, new PrescriptionComparator());
        return Collections.unmodifiableList(this.prescriptions);
    }

    public void add(Dosage prescription) {
        this.prescriptions.add(prescription);
    }
 }
