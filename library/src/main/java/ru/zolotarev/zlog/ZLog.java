package ru.zolotarev.zlog;

import android.content.Context;
import android.util.Log;

public class ZLog {

	private static String TAG = "TAG";

	private static boolean enabled = true;
	private static boolean trackInRelease = true;

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
		boolean inBuild = (BuildConfig.DEBUG || trackInRelease);
		if (enabled &&  inBuild) {
			String s = (aClass != null ? (aClass.getClass().getSimpleName()+"\n"):"") +text;
			if (e != null) {
				Log.d(TAG, s, e);
			}else{
				Log.d(TAG, s);
			}
		}
	}

	public static void trackFatalErrors(Context context){
		Thread.setDefaultUncaughtExceptionHandler(new FatalErrorHandler(context, true));
	}

	public static boolean isEnabled() {
		return enabled;
	}

	public static boolean isTrackInRelease() {
		return trackInRelease;
	}

	public static void setEnabled(final boolean enabled) {
		ZLog.enabled = enabled;
	}

	public static void setTrackInRelease(final boolean trackInRelease) {
		ZLog.trackInRelease = trackInRelease;
	}
}
