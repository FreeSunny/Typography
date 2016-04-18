package com.sunny.typography.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sunny.typography.R;
import com.sunny.typography.data.DataSource;
import com.sunny.typography.wight.TypographyView2;

public class Typography2Activity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Typography2Activity.class);
        context.startActivity(intent);
    }

    private TypographyView2 typographyView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typography2);
        findViews();
        loadData();
    }

    private void findViews() {
        typographyView2 = (TypographyView2) findViewById(R.id.typography_View2);
    }

    private void loadData() {
        typographyView2.setText(DataSource.getArray());
    }
}
