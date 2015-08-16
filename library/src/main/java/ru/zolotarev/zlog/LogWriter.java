package ru.zolotarev.zlog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

	static void write(Context context,String msg){
		write(context, msg,null);
	}

	static void write(Context context,String msg,Throwable exception){
	}



	private static String checkFirstLog(Context context){
		try {
			PackageInfo pack = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
			return "App version "+pack.versionName + " ("+pack.versionCode+")";
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
