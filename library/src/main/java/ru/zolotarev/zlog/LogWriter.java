package ru.zolotarev.zlog;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    public static void write(Context context,String msg){
        String logFile = SessionManager.getFilePath(context);

        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile,
                    true));
            buf.append(msg);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
