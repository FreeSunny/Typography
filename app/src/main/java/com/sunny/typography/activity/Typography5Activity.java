package com.sunny.typography.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunny.typography.R;
import com.sunny.typography.data.DataSource;

import org.json.JSONArray;

/**
 * 采用用add一行一行来实现
 */
public class Typography5Activity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Typography5Activity.class);
        context.startActivity(intent);
    }


    private LinearLayout root;
    private Paint leftPaint = new Paint();
    private float textSize;
    private float maxLeftWidth;
    private int middlePadding = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_typography5, null);
        setContentView(root);
        initPaint();
        loadData();
    }

    private void initPaint() {
        textSize = getResources().getDimensionPixelSize(R.dimen.text_size_13);
        leftPaint.setAntiAlias(true);
        leftPaint.setTextSize(textSize);
        leftPaint.setColor(getResources().getColor(R.color.color_black_999999));
        middlePadding = getResources().getDimensionPixelSize(R.dimen.padding_value);
    }

    private void loadData() {
        JSONArray array = DataSource.getArray();
        calculateLeftMaxWidth(array);
        if (array != null) {
            try {
                int size = array.length();
                for (int i = 0; i < size; ++i) {
                    JSONArray o = (JSONArray) array.get(i);
                    String key = o.getString(0);
                    String value = o.getString(1);
                    addItem(key, value);
                }
            } catch (Exception e) {

            }
        }
    }

    private void calculateLeftMaxWidth(JSONArray data) {
        try {
            int size = data.length();
            for (int i = 0; i < size; ++i) {
                JSONArray o = (JSONArray) data.get(i);
                String key = o.getString(0);
                String value = o.getString(1);
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                    continue;
                }
                float curWidth = leftPaint.measureText(key);
                if (curWidth > maxLeftWidth) {
                    maxLeftWidth = curWidth;
                }
            }
            maxLeftWidth = maxLeftWidth + middlePadding;
        } catch (Exception e) {

        }
    }

    private void addItem(String key, String value) {
        LinearLayout layout = getItemLayout();
        TextView left = (TextView) layout.findViewById(R.id.left);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        params.width = (int) maxLeftWidth;
        left.setLayoutParams(params);
        left.setText(key);

        TextView right = (TextView) layout.findViewById(R.id.right);
        right.setText(value);

        root.addView(layout);
    }

    private LinearLayout getItemLayout() {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.compose_item_layout, null);
        return layout;
    }
}
