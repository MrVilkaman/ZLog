package ru.zolotarev.zlog;

class Utils {
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
