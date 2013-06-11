package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

        FileOutputStream fileOutputStream = new FileOutputStream(entryFile, true);
        byte[] buffer = new Gson().toJson(entry).getBytes();
        fileOutputStream.write(buffer);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
//    public Entry get(String name) {
//        return null;
//    }
//
//    public void delete(String name) {
//
//    }
}
