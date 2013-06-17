package com.MobiSeeker.PrescriptionWatcher.test.utils;

import android.content.Intent;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowIntent;

import java.util.List;

import static org.junit.Assert.*;

public class Assert {
    public static Intent assertServiceStarted(java.lang.Class<?> expected) {
        Intent intent = Robolectric.getShadowApplication().getNextStartedService();
        assertIntentClass(expected, intent);
        return intent;
    }

    public static Intent assertActivityStarted(java.lang.Class<?> expected) {
        Intent intent = Robolectric.getShadowApplication().getNextStartedActivity();
        assertIntentClass(expected, intent);
        return intent;
    }

    public static void assertServiceNotStarted(java.lang.Class<?> expected) {
        Intent intent = Robolectric.getShadowApplication().getNextStartedService();
        assertIntentNotSame(expected, intent);
    }

    public static void assertActivityNotStarted(java.lang.Class<?> expected) {
        Intent intent = Robolectric.getShadowApplication().getNextStartedActivity();
        assertIntentNotSame(expected, intent);
    }

    public static Intent assertBroadCastAction(String action) {
        List<Intent> broadcasts = Robolectric.getShadowApplication().getBroadcastIntents();
        assertFalse(broadcasts.isEmpty());
        Intent intent = broadcasts.get(0);
        assertEquals(action, intent.getAction());
        return intent;
    }

    public static void assertNoBroadCast() {
        List<Intent> broadcasts = Robolectric.getShadowApplication().getBroadcastIntents();
        assertTrue(broadcasts.isEmpty());
    }

    private static void assertIntentNotSame(Class<?> expected, Intent intent) {
        if(intent != null) {
            ShadowIntent shadowIntent = Robolectric.shadowOf(intent);
            assertNotSame(expected, shadowIntent.getIntentClass());
        }
    }

    private static void assertIntentClass(Class<?> expected, Intent intent) {
        assertNotNull(intent);
        ShadowIntent shadowIntent = Robolectric.shadowOf(intent);
        assertEquals(expected, shadowIntent.getIntentClass());
    }
}
