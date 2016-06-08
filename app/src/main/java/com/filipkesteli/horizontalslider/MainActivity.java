package com.filipkesteli.horizontalslider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvProgress;
    private HorizontalSlider hzSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setupListeners();
    }

    private void initWidgets() {
        tvProgress = (TextView) findViewById(R.id.tvProgress);
        hzSlider = (HorizontalSlider) findViewById(R.id.hzSlider);
    }

    private void setupListeners() {
        hzSlider.setOnProgressChangedListener(new OnProgressChangedListener() {
            @Override
            public void onProgressChanged(View view, int progress) {
                //ovo ce biti razlicito na ispitu
                String report = progress + "%";
                tvProgress.setText(report);
            }
        });
    }
}
