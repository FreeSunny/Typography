package com.sunny.typography.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sunny.typography.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 该方法，是需要显示的内容先拼接成SpannableString，在onDraw方法中获取所有的char字符，一个一个比较
 * 当为分号是，表示为key与value的分隔符。
 */
public class TypographyView3 extends TextView {

    private Paint leftPaint = new Paint();
    private Paint rightPaint = new Paint();
    int width;
    private String text;
    private float textSize;
    float maxLeftWidth = 0;
    private int middlePadding = 0;

    public TypographyView3(Context context) {
        super(context);
        init();
    }

    public TypographyView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TypographyView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textSize = getResources().getDimensionPixelSize(R.dimen.text_size_13);
        textSize = getResources().getDimensionPixelSize(R.dimen.text_size_13);
        leftPaint.setAntiAlias(true);
        leftPaint.setTextSize(textSize);
        leftPaint.setColor(getResources().getColor(R.color.color_black_999999));
        rightPaint.setAntiAlias(true);
        rightPaint.setTextSize(textSize);
        rightPaint.setColor(getResources().getColor(R.color.color_black));
        middlePadding = getResources().getDimensionPixelSize(R.dimen.padding_value);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();// 整个textView的宽度
    }

    public void setText(JSONArray data) {
        if (data == null) {
            return;
        }
        try {
            int size = data.length();
            for (int i = 0; i < size; ++i) {
                JSONArray o = (JSONArray) data.get(i);
                String key = o.getString(0);
                float v = leftPaint.measureText(key);
                if (v > maxLeftWidth) {
                    maxLeftWidth = v;
                }
            }
            maxLeftWidth += middlePadding;
            SpannableStringBuilder ssb = new SpannableStringBuilder();
            for (int i = 0; i < size; ++i) {
                addItem((JSONArray) data.get(i), ssb, i != 0);
            }
            setText(ssb, BufferType.SPANNABLE);
        } catch (Exception e) {

        }
    }

    private void addItem(JSONArray item, SpannableStringBuilder ssb, boolean breakLine) {
        try {
            if (item == null || item.length() == 0) {
                return;
            }
            String key = item.getString(0);
            String value = (item.length() >= 2) ? item.getString(1) : "";
            if (TextUtils.isEmpty(key) && TextUtils.isEmpty(value)) {
                return;
            }
            if (breakLine) {// 换行
                ssb.append("\r\n");
                ssb.append("\r\n");
            }
            SpannableString span = new SpannableString(key);
            //            span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, key
            // .length(),
            //                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            ssb.append(span);
            ssb.append(value);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        int lineCount = 0;
        text = this.getText().toString();
        if (text == null)
            return;
        char[] textCharArray = text.toCharArray();
        // 已绘的宽度
        float drawWidth = 0;
        float charWidth;
        Paint paint = leftPaint;
        for (int i = 0; i < textCharArray.length; i++) {
            charWidth = leftPaint.measureText(textCharArray, i, 1);

            if (textCharArray[i] == '\n') {
                lineCount++;
                drawWidth = 0;
                paint = leftPaint;
                continue;
            }
            if (width - drawWidth < charWidth) {
                lineCount++;
                drawWidth = maxLeftWidth;
            }
            if (i > 1 && textCharArray[i - 1] == ':') {
                drawWidth = maxLeftWidth;
                paint = rightPaint;
            }
            canvas.drawText(textCharArray, i, 1, drawWidth, (lineCount + 1) * textSize, paint);
            drawWidth += charWidth;
        }
        //may be need set height
        //setHeight((lineCount + 1) * (int) textSize + 5);
    }
}
