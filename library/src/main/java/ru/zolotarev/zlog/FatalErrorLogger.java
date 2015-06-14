package ru.zolotarev.zlog;

import android.content.Context;

public class FatalErrorLogger {

    public static void init(Context context){
        Thread.setDefaultUncaughtExceptionHandler(new FatalErrorHandler(context, true));
    }
}
