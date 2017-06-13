package com.twisty.interlude.example;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twisty.interlude.R;
import com.twisty.interlude.lib.IndicatorType;
import com.twisty.interlude.lib.Interlude;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Interlude interlude = new Interlude(getSupportFragmentManager());
        interlude.setIndicatorType(IndicatorType.BallGridBeatIndicator);
        interlude.setDismissCallback(dialogInterface -> {
            Log.e(getLocalClassName(), "Dismiss");
            return null;
        });
        interlude.setCancelCallback(dialogInterface -> {
            Log.e(getLocalClassName(), "Cancel");
            return null;
        });
        findViewById(R.id.layout).setOnClickListener(v -> {
            interlude.show();
            new Handler().postDelayed(interlude::dismiss, 5000);
        });
    }
}
