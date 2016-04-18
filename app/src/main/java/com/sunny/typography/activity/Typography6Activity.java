package com.sunny.typography.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sunny.typography.R;
import com.sunny.typography.data.DataSource;
import com.sunny.typography.wight.TypographyView4;

/**
 * 才用draw text
 */
public class Typography6Activity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Typography6Activity.class);
        context.startActivity(intent);
    }

    private TypographyView4 typographyView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typography6);
        findViews();
        loadData();
    }

    private void findViews() {
        typographyView1 = (TypographyView4) findViewById(R.id.typography_View4);
    }

    private void loadData() {
        typographyView1.setText(DataSource.getArray());
    }
}
