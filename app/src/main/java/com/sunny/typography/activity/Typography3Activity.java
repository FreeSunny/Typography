package com.sunny.typography.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sunny.typography.R;
import com.sunny.typography.data.DataSource;
import com.sunny.typography.wight.TypographyView3;

public class Typography3Activity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Typography3Activity.class);
        context.startActivity(intent);
    }

    private TypographyView3 typographyView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typography3);
        findViews();
        loadData();
    }

    private void findViews() {
        typographyView3 = (TypographyView3) findViewById(R.id.typography_View3);
    }

    private void loadData() {
        typographyView3.setText(DataSource.getArray());
    }
}
