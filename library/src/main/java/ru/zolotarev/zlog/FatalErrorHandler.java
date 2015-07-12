package ru.zolotarev.zlog;

import android.content.Context;

public class FatalErrorHandler implements Thread.UncaughtExceptionHandler  {
    private final Thread.UncaughtExceptionHandler previousHandler;
    private Context context;

    public FatalErrorHandler(Context context, boolean chained) {
        this.context = context;
        if(chained) {
            previousHandler = Thread.getDefaultUncaughtExceptionHandler();
        }else {
            previousHandler = null;
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogWriter.write(context,"FATAL EXCEPTION",ex);
        if(previousHandler != null)
            previousHandler.uncaughtException(thread, ex);
    }
}
