package ru.zolotarev.zlog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    public static void write(Context context,String msg){
        write(context, msg,null);
    }
    public static void write(Context context,String msg,Throwable exception){
        String logFile = SessionManager.getFilePath(context);

        if (exception == null) {
            if (isFirst) {
                String vers = checkFirstLog(context);
                if (vers != null) {
                    print(logFile, vers);
                }
            }
            print(logFile,msg);
        }else{
            StringBuilder builder = new StringBuilder();
            if (isFirst) {
                String vers = checkFirstLog(context);
                if (vers != null) {
                    builder.append(vers)
                            .append("\n");
                }
            }
            builder.append(msg)
                    .append("\n");
            processThrowable(exception, builder);
            print(logFile,builder.toString());
        }

    }

    private static void processThrowable(Throwable exception, StringBuilder builder) {
        if(exception == null)
            return;
        StackTraceElement[] stackTraceElements = exception.getStackTrace();
        builder
                .append("Exception: ").append(exception.getClass().getName()).append("\n")
                .append("Message: ").append(exception.getMessage()).append("\nStacktrace:\n");
        for(StackTraceElement element : stackTraceElements) {
            builder.append("\t").append(element.toString()).append("\n");
        }
        processThrowable(exception.getCause(), builder);
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
