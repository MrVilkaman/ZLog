package ru.zolotarev.zlog.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.zolotarev.zlog.FatalErrorLogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FatalErrorLogger.init(this);

        Object o = null;
        o.toString();
    }

}
