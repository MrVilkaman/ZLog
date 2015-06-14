package ru.zolotarev.zlog;

import android.util.Log;

public class FatalErrorHandler implements Thread.UncaughtExceptionHandler  {
    private final Thread.UncaughtExceptionHandler previousHandler;

    public FatalErrorHandler(boolean chained) {
        if(chained) {
            previousHandler = Thread.getDefaultUncaughtExceptionHandler();
        }else {
            previousHandler = null;
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.d("TAG","it is work!!");

        if(previousHandler != null)
            previousHandler.uncaughtException(thread, ex);
    }
}
