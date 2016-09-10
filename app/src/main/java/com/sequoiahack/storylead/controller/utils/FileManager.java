package com.sequoiahack.storylead.controller.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by zac on 10/09/16.
 */
public class FileManager {

    public static File[] getFiles() {
        String path = Environment.getExternalStorageDirectory().toString() + "/MIUI/sound_recorder/call_rec/";
        File f = new File(path);
        File recordedFile[] = f.listFiles();
        /*for (int i = 0; i < file.length; i++) {
            showLog("FileName:" + file[i].getName());
        }*/
        return recordedFile;
    }

    public static String getNameFromFile(File file) {
        String fileName = file.getName();
        String[] split1 = fileName.split("@");
        String[] split2 = split1[1].split("\\(");
        return split2[0];
    }

    public static String getNumberFromFile(File file) {
        String fileName = file.getName();
        String[] split1 = fileName.split("\\(");
        String[] split2 = split1[1].split("\\)");
        return split2[0];
    }
}
