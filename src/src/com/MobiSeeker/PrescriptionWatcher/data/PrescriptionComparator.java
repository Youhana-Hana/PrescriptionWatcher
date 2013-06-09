package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.Comparator;

public class PrescriptionComparator implements Comparator<Dosage> {

    @Override
    public int compare(Dosage first, Dosage second) {
        if (first.getTime() == null && second.getTime() == null)
            return 0;

        if (first.getTime() == null)
            return -1;

        if (second.getTime() == null)
            return 1;

        return first.getTime().compareTo(second.getTime());
    }

}
