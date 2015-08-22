package ru.zolotarev.zlog;

import android.util.Log;

public class ConsoleWriter {

	private ConsoleWriter() {
	}

	static void write(final String tag, String text, Throwable exception, Object aClass) {

		StringBuilder builder = new StringBuilder();

		if (aClass != null) {
			builder.append("Location: ")
			       .append(aClass.getClass().getName());
			builder.append("\n");
		}

		builder.append(text);
		builder.append("\n");

		if (exception != null) {
			Log.d(tag, builder.toString(),exception);
		}else{
			Log.d(tag,builder.toString());
		}


	}
}
