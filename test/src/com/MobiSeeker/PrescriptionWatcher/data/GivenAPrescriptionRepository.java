package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.sql.Time;
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
        this.entry = new Entry(this.context, "NAME", new Date(), new Date(), Time.valueOf("10:00:00"), Time.valueOf("17:00:00"), 2, 3, "comment");

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
        Entry entry2 = new Entry(this.context, "NAME2", new Date(), new Date(), Time.valueOf("10:00:00"), Time.valueOf("17:00:00"), 2, 4, "comment");

        this.repository.save(this.context, this.entry);
        this.repository.save(this.context, entry2);

        int count = this.repository.getCount(this.context);

        assertEquals(2, count);
    }

    @Test
    public void whenCallingGetEntriesShouldNotThrow() throws Exception{
        ArrayList<Entry> entries= this.repository.getEntries(this.context);

        assertNotNull(entries);
    }

    @Test
    public void whenCallingGetEntriesWithZeroEntriesShouldReturnEmtpyList()  throws Exception{
        ArrayList<Entry> entries= this.repository.getEntries(this.context);

        assertNotNull(entries);
        assertTrue(entries.isEmpty());
    }

    @Test
    public void whenCallingGetEntriesWithFilesShouldReturnExpected() throws Exception{
        this.repository.save(this.context, this.entry);

        ArrayList<Entry> entries= this.repository.getEntries(this.context);

        assertNotNull(entries);
        assertEquals(1, entries.size());
        assertEquals(this.entry.getMedicineName(), entries.get(0).getMedicineName());
        assertEquals(this.entry.getStartTime(), entries.get(0).getStartTime());
        assertEquals(this.entry.getStartDate().toString(), entries.get(0).getStartDate().toString());
        assertEquals(this.entry.getComment(), entries.get(0).getComment());
        assertEquals(this.entry.getEndDate().toString(), entries.get(0).getEndDate().toString());
        assertEquals(this.entry.getEndTime(), entries.get(0).getEndTime());
        assertEquals(this.entry.getDosage(), entries.get(0).getDosage());
        assertEquals(this.entry.getTimesPerDay(), entries.get(0).getTimesPerDay());
    }

    @Test
    public void whenCallingGetEntriesWithMultipleFilesShouldReturnExpected() throws Exception{
        Date startDate2 = new Date(new Date().getTime() + 10 *60 *1000);
        Entry entry2 = new Entry(this.context, "NAME2", startDate2, startDate2, Time.valueOf("10:00:00"), Time.valueOf("17:00:00"), 2, 4, "comment");

        Date startDate3 = new Date(new Date().getTime() + 20 *60 *1000);
        Entry entry3 = new Entry(this.context, "NAME3", startDate3, startDate3, Time.valueOf("10:00:00"), Time.valueOf("17:00:00"), 2, 4, "comment");

        Date startDate4 = new Date(new Date().getTime() + 30 *60 *1000);
        Entry entry4 = new Entry(this.context, "NAME4", startDate4, startDate4, Time.valueOf("10:00:00"), Time.valueOf("17:00:00"), 2, 4, "comment");

        Date startDate5 = new Date(new Date().getTime() + 40 *60 *1000);
        Entry entry5 = new Entry(this.context, "NAME5", startDate5, startDate5, Time.valueOf("10:00:00"), Time.valueOf("17:00:00"), 2, 4, "comment");


        this.repository.save(this.context, this.entry);
        this.repository.save(this.context, entry2);
        this.repository.save(this.context, entry3);
        this.repository.save(this.context, entry4);
        this.repository.save(this.context, entry5);

        ArrayList<Entry> entries= this.repository.getEntries(this.context);

        assertNotNull(entries);
        assertEquals(5, entries.size());

        assertEquals(this.entry.getMedicineName(), entries.get(0).getMedicineName());
        assertEquals(this.entry.getStartTime(), entries.get(0).getStartTime());
        assertEquals(this.entry.getStartDate().toString(), entries.get(0).getStartDate().toString());
        assertEquals(this.entry.getComment(), entries.get(0).getComment());
        assertEquals(this.entry.getEndDate().toString(), entries.get(0).getEndDate().toString());
        assertEquals(this.entry.getEndTime(), entries.get(0).getEndTime());
        assertEquals(this.entry.getDosage(), entries.get(0).getDosage());
        assertEquals(this.entry.getTimesPerDay(), entries.get(0).getTimesPerDay());

        assertEquals(entry2.getMedicineName(), entries.get(1).getMedicineName());
        assertEquals(entry2.getStartTime(), entries.get(1).getStartTime());
        assertEquals(entry2.getStartDate().toString(), entries.get(1).getStartDate().toString());
        assertEquals(entry2.getComment(), entries.get(1).getComment());
        assertEquals(entry2.getEndDate().toString(), entries.get(1).getEndDate().toString());
        assertEquals(entry2.getEndTime(), entries.get(1).getEndTime());
        assertEquals(entry2.getDosage(), entries.get(1).getDosage());
        assertEquals(entry2.getTimesPerDay(), entries.get(1).getTimesPerDay());

        assertEquals(entry5.getMedicineName(), entries.get(4).getMedicineName());
        assertEquals(entry5.getStartTime(), entries.get(4).getStartTime());
        assertEquals(entry5.getStartDate().toString(), entries.get(4).getStartDate().toString());
        assertEquals(entry5.getComment(), entries.get(4).getComment());
        assertEquals(entry5.getEndDate().toString(), entries.get(4).getEndDate().toString());
        assertEquals(entry5.getEndTime(), entries.get(4).getEndTime());
        assertEquals(entry5.getDosage(), entries.get(4).getDosage());
        assertEquals(entry5.getTimesPerDay(), entries.get(4).getTimesPerDay());
    }
}
