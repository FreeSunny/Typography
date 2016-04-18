package com.sunny.typography.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sunny.typography.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListener();
    }

    private void setListener() {
        findViewById(R.id.implement1).setOnClickListener(this);
        findViewById(R.id.implement2).setOnClickListener(this);
        findViewById(R.id.implement3).setOnClickListener(this);
        findViewById(R.id.implement4).setOnClickListener(this);
        findViewById(R.id.implement5).setOnClickListener(this);
        findViewById(R.id.implement6).setOnClickListener(this);
        findViewById(R.id.implement7).setOnClickListener(this);
        findViewById(R.id.implement8).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.implement1:
                Typography1Activity.start(this);
                break;
            case R.id.implement2:
                Typography2Activity.start(this);
                break;
            case R.id.implement3:
                Typography3Activity.start(this);
                break;
            case R.id.implement4:
                Typography4Activity.start(this);
                break;
            case R.id.implement5:
                Typography5Activity.start(this);
                break;
            case R.id.implement6:
                Typography6Activity.start(this);
                break;
            case R.id.implement7:
                Typography7Activity.start(this);
                break;
            case R.id.implement8:
                Typography8Activity.start(this);
                break;

        }
    }
}
