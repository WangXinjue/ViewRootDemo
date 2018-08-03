package com.example.xinjuewang.viewrootdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.security.Permission;

/**
 * Created by xinjue.wang on 2018/3/15.
 */

public class FloatViewAcitivity extends Activity {

    Button mFloatViewButton ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.float_view_activity);

        requestAlertWindowPermission();

        mFloatViewButton = (Button) findViewById(R.id.start_float_view);

        mFloatViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager.LayoutParams lp = createLayoutParams();
                Button btn = new Button(v.getContext());
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWindowManager().removeView(v);
                    }
                });
                btn.setText("Click me to dismiss!");
                getWindowManager().addView(btn,lp);
            }
        });
    }

    private WindowManager.LayoutParams createLayoutParams() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        //TYPE_PHONE只要Activity不销毁，就有悬浮框，在屏幕层
        lp.type = WindowManager.LayoutParams.TYPE_PHONE;
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

//        lp.
//        getWindowManager().mWindowAttributes.inputFeatures
//                WindowManager.LayoutParams.INPUT_FEATURE_NO_INPUT_CHANNEL;
        View.MeasureSpec.EXACTLY;
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        return lp;
    }

    private void requestAlertWindowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(getApplicationContext())) {
                //启动Activity让用户授权
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,100);
            }
        }
    }
}
