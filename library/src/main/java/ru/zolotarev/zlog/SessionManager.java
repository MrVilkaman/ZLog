package ru.zolotarev.zlog;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

class SessionManager {

    private static String currentPath;

	private SessionManager() {
	}

	private static String getBasePath(Context context){
		return Environment.getExternalStorageDirectory()+ File.separator + getAppName(context)+ File.separator;
	}

    static String getFilePath(Context context){

        if (currentPath == null) {
            currentPath = getBasePath(context);
            Date date = new Date();
            currentPath += new SimpleDateFormat(Constants.DATE_FORMAT).format(date);
            currentPath += File.separator;
            File dir = new File(currentPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            currentPath += new SimpleDateFormat(Constants.TIME_FORMAT).format(date);
            currentPath += Constants.FILE_EXT;
        }

        return currentPath;
    }

    private static String getAppName(Context context) {
        final PackageManager pm = context.getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(context.getPackageName(), 0);

        } catch (final PackageManager.NameNotFoundException e) {
	        Log.e(ZLog.tag, "Cann't get application info", e);
            ai = null;
        }
        return ai != null ? pm.getApplicationLabel(ai).toString() : Constants.DEFAULT_APP_NAME;
    }
}
