package ru.zolotarev.zlog.sample;

import android.app.Activity;
import android.os.Bundle;

import ru.zolotarev.zlog.ZLog;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZLog.trackFatalErrors(this);

	    ZLog.setEnable(true);
//	    ZLog.setBuildContig(BuildConfig.DEBUG);

	    ZLog.i("Simple text only in console");
	    ZLog.i("Simple text only in console with class location",this);

	    ZLog.d("Simple text to file and console");

	    int value = 45;
	    try {
		    value /= 0;
	    } catch (Exception e) {
		    ZLog.d("Math operation",e);
	    }

	    Object o = null;
	    o.toString();

    }

}
