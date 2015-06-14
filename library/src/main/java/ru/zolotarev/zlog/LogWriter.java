package ru.zolotarev.zlog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    public static void write(Context context,String msg){
        String logFile = SessionManager.getFilePath(context);
        if (isFirst) {
            String vers = checkFirstLog(context);
            if (vers != null) {
                print(logFile,vers);
            }
        }
        print(logFile,msg);
    }

    private static void print(String logFile, String msg){
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

    private static boolean isFirst = true;
    private static String checkFirstLog(Context context){

        try {
            PackageInfo pack = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            isFirst = false;
            return "App version "+pack.versionName + " ("+pack.versionCode+")";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
