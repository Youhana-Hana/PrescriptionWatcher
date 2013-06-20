package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.Comparator;

public class EntryComparator implements Comparator <Entry>{
    @Override
    public int compare(Entry first, Entry second) {
        if (first == null && second == null)
            return 0;

        if (first == null)
            return -1;

        if (second == null)
            return 1;

        return first.getStartDate().compareTo(second.getStartDate());
    }
}
