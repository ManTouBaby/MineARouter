package com.hrw.login;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.hrw.common.utils.MtStatusBarHelper;

public class ACLogin extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aclogin);
        MtStatusBarHelper.instance(this)
                .setBGColor(Color.TRANSPARENT)
                .setFullScreen(true);
    }
}
