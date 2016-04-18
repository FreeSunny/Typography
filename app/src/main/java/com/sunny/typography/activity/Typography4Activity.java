package com.sunny.typography.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunny.typography.R;
import com.sunny.typography.data.DataSource;
import com.sunny.typography.util.DisplayUtil;

import org.json.JSONArray;

/**
 * 采用GridLayout来实现，但是该方式有bug, 如果描述文案太长会导致显示不完全
 */
public class Typography4Activity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Typography4Activity.class);
        context.startActivity(intent);
    }

    private LinearLayout root;
    private Paint leftPaint = new Paint();
    private float textSize;
    private float maxLeftWidth;
    private int middlePadding = 0;
    private float maxRightWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_typography4, null);
        setContentView(root);
        initPaint();
        findViews();
        loadData();
    }

    private void initPaint() {
        textSize = getResources().getDimensionPixelSize(R.dimen.text_size_13);
        leftPaint.setAntiAlias(true);
        leftPaint.setTextSize(textSize);
        leftPaint.setColor(getResources().getColor(R.color.color_black_999999));
        middlePadding = getResources().getDimensionPixelSize(R.dimen.padding_value);
    }

    private void findViews() {

    }

    private void loadData() {
        addGridLayout(DataSource.getArray());
        TextView view = new TextView(this);
        view.setText("修改后的实现");
        view.setGravity(Gravity.CENTER);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 160));
        root.addView(view);
        addModifyGridLayout(DataSource.getArray());
    }

    private void addGridLayout(JSONArray data) {
        try {
            GridLayout layout = createGridLayout();
            int size = data.length();
            for (int i = 0; i < size; ++i) {
                JSONArray item = (JSONArray) data.get(i);
                String key = item.getString(0);
                String value = (item.length() >= 2) ? item.getString(1) : "";
                GridLayout.Spec row = GridLayout.spec(i);
                GridLayout.Spec col1 = GridLayout.spec(0);
                GridLayout.Spec col2 = GridLayout.spec(1);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(row, col1);

                TextView title = getLeftTextView(key);
                layout.addView(title, params);

                params = new GridLayout.LayoutParams(row, col2);
                TextView desc = getRightTextView(value);
                layout.addView(desc, params);
            }
            root.addView(layout);
        } catch (Exception e) {

        }
    }

    @NonNull
    private TextView getRightTextView(String value) {
        TextView desc = new TextView(this);
        desc.setTextSize(13);
        desc.setTextColor(getResources().getColor(R.color.black));
        desc.setText(value);
        return desc;
    }

    @NonNull
    private TextView getLeftTextView(String key) {
        TextView title = new TextView(this);
        title.setText(key);
        title.setPadding(0, middlePadding, middlePadding, 0);
        title.setTextColor(getResources().getColor(R.color.color_black_999999));
        title.setTextSize(13);
        return title;
    }

    private void addModifyGridLayout(JSONArray data) {
        try {
            calculateLeftMaxWidth(data);
            GridLayout layout = createGridLayout();
            int size = data.length();
            for (int i = 0; i < size; ++i) {
                JSONArray item = (JSONArray) data.get(i);
                GridLayout.Spec row = GridLayout.spec(i);

                String key = item.getString(0);
                GridLayout.Spec col1 = GridLayout.spec(0);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(row, col1);

                TextView title = getLeftTextView(key);
                layout.addView(title, params);

                String value = (item.length() >= 2) ? item.getString(1) : "";
                GridLayout.Spec col2 = GridLayout.spec(1);
                params = new GridLayout.LayoutParams(row, col2);

                TextView desc = getRightTextView(value);
                params.width = (int) maxRightWidth;
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                layout.addView(desc, params);
            }
            root.addView(layout);
        } catch (Exception e) {

        }
    }

    private void calculateLeftMaxWidth(JSONArray data) {
        try {
            DisplayUtil.init(this);// 这个可以在应用程序起来的时候init
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
            maxRightWidth = DisplayUtil.screenWidth - DisplayUtil.dp2px(this, 32 + 10) - maxLeftWidth;
        } catch (Exception e) {

        }
    }

    private GridLayout createGridLayout() {
        GridLayout layout = new GridLayout(this);
        layout.setColumnCount(2);
        //layout.setRowCount(5);
        layout.setOrientation(GridLayout.HORIZONTAL);
        return layout;
    }
}
