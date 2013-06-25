package com.MobiSeeker.PrescriptionWatcher.connection;


import android.content.Context;
import android.os.PowerManager;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowPowerManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class GivenAWakeLockManager {

    Context context = null;

    WakeLockManager wakeLockManager = null;

    PowerManager powerManager;

    ShadowPowerManager shadowPowerManager;

    @Mock
    PowerManager.WakeLock wakeLock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.context = Robolectric.getShadowApplication().getApplicationContext();

        wakeLockManager = new WakeLockManager(context);

        this.powerManager = (PowerManager) Robolectric.application.getSystemService(Context.POWER_SERVICE);

        this.shadowPowerManager = Robolectric.shadowOf(powerManager);
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.wakeLockManager);
    }

    @Test
    public void whenCallingAcquireShouldNotThrow() {
        this.wakeLockManager.acquire();

        assertNotNull(this.wakeLockManager.mWakeLock);
        assertTrue(this.wakeLockManager.mWakeLock.isHeld());
    }

    @Test
    public void whenCallingAcquireAndLoclPreviouselyExistsShouldNotAllocateNewLock() {
        this.wakeLock = this.shadowPowerManager.newWakeLock(0, "TAG");
        this.wakeLockManager.mWakeLock = this.wakeLock;

        this.wakeLockManager.acquire();

        assertNotNull(this.wakeLockManager.mWakeLock);
        assertTrue(this.wakeLockManager.mWakeLock.isHeld());
        assertEquals(this.wakeLock, this.wakeLockManager.mWakeLock);
    }

    @Test
    public void whenCallingAcquireAndLockAlreadyHeldShouldReleaseFirst() {
        this.wakeLockManager.mWakeLock = this.wakeLock;
        doReturn(true).when(this.wakeLock).isHeld();

        this.wakeLockManager.acquire();

        assertNotNull(this.wakeLockManager.mWakeLock);
        assertTrue(this.wakeLockManager.mWakeLock.isHeld());
        verify(this.wakeLock).release();
    }

    @Test
    public void whenCallingReleaseShouldNotThrow() {
        this.wakeLockManager.acquire();
        this.wakeLockManager.releaseWakeLock();

        assertNotNull(this.wakeLockManager.mWakeLock);
        assertFalse(this.wakeLockManager.mWakeLock.isHeld());
    }

    @Test
    public void whenCallingReleaseBeforeCallAcquireShouldNotThrow() {
        this.wakeLockManager.releaseWakeLock();

        assertNull(this.wakeLockManager.mWakeLock);
    }

    @Test
    public void whenCallingReleaseAndIsHledFalseShouldNotRelease() {
       this.wakeLockManager.mWakeLock = this.wakeLock;
       doReturn(false).when(this.wakeLock).isHeld();

       this.wakeLockManager.releaseWakeLock();

        assertFalse(this.wakeLockManager.mWakeLock.isHeld());
        verify(this.wakeLock, never()).release();
    }
}
