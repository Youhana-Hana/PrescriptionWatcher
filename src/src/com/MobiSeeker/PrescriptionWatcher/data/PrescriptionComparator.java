package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.Comparator;

public class PrescriptionComparator implements Comparator<Prescription> {

    @Override
    public int compare(Prescription first, Prescription second) {
        return first.getTime().compareTo(second.getTime());
    }

}
