package ru.zolotarev.zlog;

import android.content.Context;
import android.os.Build;

import java.io.File;
import java.io.IOException;

public class FileWriter {


	static void write(Context context,String text, Throwable exception, Object aClass){

		String logFile = SessionManager.getFilePath(context);
		File file = new File(logFile);
		StringBuilder builder = new StringBuilder();
		if (!file.exists()) {
			try {
				file.createNewFile();
				getDeviceInfo(builder);
				builder.append("\n");
			} catch (IOException e) {
				return;
			}
		}
		if (aClass != null) {
			builder.append("Location: ")
			       .append(aClass.getClass().getSimpleName());
		}

		builder.append("\n");
		builder.append(text);
		builder.append("\n");

		if (exception != null) {
			FileUtils.processThrowable(exception, builder);
		}

		FileUtils.print(file,builder.toString());
	}

	static void getDeviceInfo(StringBuilder builder){
		builder.append("App version ")
		       .append(BuildConfig.VERSION_NAME)
		       .append(" (")
		       .append(BuildConfig.VERSION_CODE)
		       .append(")")
		       .append("\nDebug-infos:")
		       .append("\n OS Version: ")
		       .append(System.getProperty("os.version"))
		       .append("(")
		       .append(Build.VERSION.INCREMENTAL)
		       .append(")")
		       .append("\n OS API Level: ")
		       .append(Build.VERSION.SDK_INT)
		       .append("\n Device: ")
		       .append(Build.DEVICE)
		       .append("\n Model (and Product): ")
		       .append(Build.MODEL)
		       .append(" (")
		       .append(Build.PRODUCT)
		       .append(")")
		       .append("\n RELEASE: ")
		       .append(Build.VERSION.RELEASE)
		       .append("\n BRAND: ")
		       .append(Build.BRAND)
		       .append("\n DISPLAY: ")
		       .append(Build.DISPLAY);

		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
			builder.append("\n CPU_ABIS: ")
			       .append(Build.SUPPORTED_ABIS)
			       .append("\n CPU_ABIS_32: ")
			       .append(Build.SUPPORTED_32_BIT_ABIS)
			       .append("\n CPU_ABIS_64: ")
			       .append(Build.SUPPORTED_64_BIT_ABIS);
		}else{
			builder.append("\n CPU_ABI: ")
			       .append(Build.CPU_ABI)
			       .append("\n CPU_ABI2: ")
			       .append(Build.CPU_ABI2);
		}

		builder.append("\n UNKNOWN: ")
		       .append(android.os.Build.UNKNOWN)
		       .append("\n HARDWARE: ")
		       .append(android.os.Build.HARDWARE)
		       .append("\n Build ID: ")
		       .append(android.os.Build.ID)
		       .append("\n MANUFACTURER: ")
		       .append(android.os.Build.MANUFACTURER)
		       .append("\n SERIAL: ")
		       .append(android.os.Build.SERIAL)
		       .append("\n USER: ")
		       .append(android.os.Build.USER)
		       .append("\n HOST: ")
		       .append(android.os.Build.HOST);
	}
}
