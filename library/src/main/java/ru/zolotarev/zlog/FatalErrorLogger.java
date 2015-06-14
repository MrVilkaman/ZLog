package ru.zolotarev.zlog;

public class FatalErrorLogger {

    public static void init(){
        Thread.setDefaultUncaughtExceptionHandler(new FatalErrorHandler(true));
    }
}
