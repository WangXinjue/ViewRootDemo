package com.example.xinjuewang.viewrootdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button btnFloatView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFloatView = (Button) findViewById(R.id.float_view);

        btnFloatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setComponent(new ComponentName(v.getContext(),FloatViewAcitivity.class));
                startActivity(i);
            }
        });
    }
}
