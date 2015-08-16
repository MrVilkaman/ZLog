package ru.zolotarev.zlog;

import android.content.Context;

public class ZLog {

	private static String TAG = "TAG";

	private static boolean trackInRelease = true;
	private static boolean enable = true;
	private static Context context;

	public static void d(String text, Object aClass){
		d(text, null, aClass);
	}

	public static void d(String text){
		d(text, null, null);
	}

	public static void d(String text, Throwable e){
		d(text, e, null);
	}

	public static void d(String text, Throwable e, Object aClass){
		if (enable) {
			if (trackInRelease) {
				ConsoleWriter.write(TAG, text, e, aClass);
			}
			if (context != null) {
				FileWriter.write(context,text, e, aClass);
			}
		}
	}

	public static void i(String text, Object aClass){
		i(text, null, aClass);
	}

	public static void i(String text){
		i(text, null, null);
	}

	public static void i(String text, Throwable e){
		i(text, e, null);
	}

	public static void i(String text, Throwable e, Object aClass){
		if (trackInRelease) {
			ConsoleWriter.write(TAG, text, e, aClass);
		}
	}

	public static void e(String text, Object aClass){
		e(text, null, aClass);
	}

	public static void e(String text){
		e(text, null, null);
	}

	public static void e(String text, Throwable e){
		e(text, e, null);
	}

	public static void e(String text, Throwable e, Object aClass){
		if (trackInRelease) {
			ConsoleWriter.write(TAG, text, e, aClass);
		}
		if (context != null) {
			FileWriter.write(context,text, e, aClass);
		}
	}

	public static void trackFatalErrors(Context context){
		setContext(context);
		Thread.setDefaultUncaughtExceptionHandler(new FatalErrorHandler(context, true));
	}

	public static boolean isEnable() {
		return enable;
	}

	public static void setEnable(final boolean enable) {
		ZLog.enable = enable;
	}

	public static boolean isTrackInRelease() {
		return trackInRelease;
	}

	public static void setBuildContig(final boolean trackInRelease) {
		ZLog.trackInRelease = trackInRelease;
	}

	public static void setTAG(final String TAG) {
		ZLog.TAG = TAG;
	}

	public static void setContext(final Context context) {
		ZLog.context = context;
	}
}
