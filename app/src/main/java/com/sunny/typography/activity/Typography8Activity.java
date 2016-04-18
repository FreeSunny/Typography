package com.sunny.typography.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sunny.typography.R;
import com.sunny.typography.data.DataSource;
import com.sunny.typography.wight.TypographyView1;

/**
 * 才用draw text
 */
public class Typography8Activity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Typography8Activity.class);
        context.startActivity(intent);
    }

    private TypographyView1 typographyView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typography8);
        findViews();
        loadData();
    }

    private void findViews() {
        typographyView1 = (TypographyView1) findViewById(R.id.typography_View1);
    }

    private void loadData() {
        typographyView1.setText(DataSource.getArray());
    }
}
