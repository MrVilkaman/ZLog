package ru.zolotarev.zlog.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.zolotarev.zlog.ZLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZLog.trackFatalErrors(this);

	    ZLog.d("Simple text");
	    ZLog.d("Simple text with class",this);

	    int i = 0;
	    try {
		    int x = 35/0;
	    } catch (Exception e) {
		    ZLog.d("Simple text with Exception",e);
		    ZLog.d("Simple text with Exception",e,this);

	    }

	    ZLog.setEnabled(false);
	    ZLog.d("setEnabled(false)");


        Object o = null;
        o.toString();
    }

}
