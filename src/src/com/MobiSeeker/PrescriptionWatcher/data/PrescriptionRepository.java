package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PrescriptionRepository {

    protected
    final static String LOCAL_DIR_NAME = "local";

    public void save(Context context, Entry entry) throws Exception {

        File local = getLocalFolder(context);

        if (!local.exists()) {
            local.mkdir();
        }

        this.createFile(entry, local);
    }

    public int getCount(Context context) {
        File local = getLocalFolder(context);
        if (!local.exists()) {
            return 0;
        }

        File[] files = local.listFiles();
        if (files == null) {
            return 0;
        }

        return files.length;
    }

    private File getLocalFolder(Context context) {
        return new File(context.getFilesDir().getPath(), LOCAL_DIR_NAME);
    }

    private void createFile(Entry entry, File local) throws IOException {
        File entryFile = new File(local.getPath(), entry.getMedicineName());

        if (!entryFile.exists()) {
            entryFile.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(entryFile, false);
        byte[] buffer = new Gson().toJson(entry).getBytes("UTF-8");
        fileOutputStream.write(buffer);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public ArrayList<Entry> getEntries(Context context) throws Exception{

        ArrayList<Entry> entries = new ArrayList<Entry>();

        File local = getLocalFolder(context);
        if (!local.exists()) {
            return entries;
        }

        File[] files = local.listFiles();
        if (files == null) {
            return entries;
        }

        for (File file : files) {
            Entry entry = getEntryFromFile(file);
            entries.add(entry);
        }

        return entries;
    }

    private Entry getEntryFromFile(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[fileInputStream.available()];
        int length = fileInputStream.read(buffer);
        fileInputStream.close();

        String content = new String(buffer, 0, length, "UTF-8");
        return new Gson().fromJson(content, Entry.class);
    }
}
