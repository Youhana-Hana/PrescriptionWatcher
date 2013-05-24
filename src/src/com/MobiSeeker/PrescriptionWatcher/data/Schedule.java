package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Schedule {

    private ArrayList<Prescription> prescriptions = null;

    public Schedule() {
        this.prescriptions = new ArrayList<Prescription>();
    }

    List<Prescription> getPrescriptions() {
        return Collections.unmodifiableList(this.prescriptions);
    }
}
