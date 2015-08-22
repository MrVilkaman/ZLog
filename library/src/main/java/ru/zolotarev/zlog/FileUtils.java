package ru.zolotarev.zlog;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class FileUtils {

	private FileUtils() {
	}

	static void print(File file, String msg){
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(file,true));
			buf.append(msg);
			buf.newLine();
			buf.close();
		} catch (IOException e) {
			Log.e(ZLog.tag,"Cann't write file",e);
		}
	}

	static void processThrowable(Throwable exception, StringBuilder builder) {
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
}
