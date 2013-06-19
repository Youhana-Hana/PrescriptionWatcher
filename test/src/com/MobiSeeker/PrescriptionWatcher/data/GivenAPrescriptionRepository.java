package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)

public class GivenAPrescriptionRepository {

    private PrescriptionRepository repository;

    private Context context;

    private Entry entry;
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        this.repository = new PrescriptionRepository();
        this.context = Robolectric.getShadowApplication().getApplicationContext();
        this.entry = new Entry(this.context, "NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("17:00"), 2, 3, "comment");

    }

    @After
    public void tearDown() {
        File filesDir = this.context.getFilesDir();
        File local = new File(filesDir.getPath(), "local");
        local.delete();
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.repository);
    }

    @Test
    public void whenCallingSaveShouldCreateLocalFolderIfNotExists() throws Exception{

        File filesDir = this.context.getFilesDir();
        File local = new File(filesDir.getPath(), "local");
        local.deleteOnExit();

        assertFalse(local.exists());
        assertNull(local.listFiles());

        this.repository.save(this.context, this.entry);

        assertTrue(local.exists());
        assertTrue(local.isDirectory());
    }

    @Test
    public void whenCallingSaveShouldNotThrow() throws Exception{
        File filesDir = this.context.getFilesDir();
        File local = new File(filesDir.getPath(), "local");
        local.deleteOnExit();

        this.repository.save(this.context, this.entry);

        File[] localFiles = local.listFiles();

        assertNotNull(localFiles);
        assertEquals(1, localFiles.length);
        assertEquals("NAME", localFiles[0].getName());
    }

    @Test
    public void whenCallingSaveWithSameEntryNameShouldOverwriteOldOne() throws Exception{
        this.repository.save(this.context, this.entry);
        this.repository.save(this.context, this.entry);
        this.repository.save(this.context, this.entry);

        File filesDir = this.context.getFilesDir();
        File local = new File(filesDir.getPath(), "local");

        File[] localFiles = local.listFiles();

        assertNotNull(localFiles);
        assertEquals(1, localFiles.length);
        assertEquals("NAME", localFiles[0].getName());
    }

    @Test
    public void whenCallingGetCountAndNothingThereSholdReturnZero(){
        int count = this.repository.getCount(this.context);

        assertEquals(0, count);
    }

    @Test
    public void whenCallingGetCountShouldRetunExpected() throws Exception{
        this.repository.save(this.context, this.entry);
        Entry entry2 = new Entry(this.context, "NAME2", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("17:00"), 2, 4, "comment");

        this.repository.save(this.context, this.entry);
        this.repository.save(this.context, entry2);

        int count = this.repository.getCount(this.context);

        assertEquals(2, count);
    }

    @Test
    public void whenCallingGetEntriesShouldNotThrow(){
        ArrayList<Entry> entries= this.repository.getEntries(this.context);

        assertNotNull(entries);
    }
}
