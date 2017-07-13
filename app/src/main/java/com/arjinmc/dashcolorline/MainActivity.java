package com.arjinmc.dashcolorline;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((DashLine) findViewById(R.id.dashline_h))
                .setColors(Color.GRAY, Color.parseColor("#23d290"));
    }
}
