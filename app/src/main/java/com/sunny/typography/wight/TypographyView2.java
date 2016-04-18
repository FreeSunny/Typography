package com.sunny.typography.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sunny.typography.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 该方式与方式1很类似，只是所有的计算都放在了onDraw方法中。
 */
public class TypographyView2 extends TextView {

    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint();
    private int middlePadding = 0;
    int width;
    private float textSize;
    private JSONArray array;

    public TypographyView2(Context context) {
        super(context);
        init();
    }

    public TypographyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TypographyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textSize = getResources().getDimensionPixelSize(R.dimen.text_size_13);
        paint1.setAntiAlias(true);
        paint1.setTextSize(textSize);
        paint1.setColor(getResources().getColor(R.color.color_black_999999));
        paint2.setAntiAlias(true);
        paint2.setTextSize(textSize);
        paint2.setColor(getResources().getColor(R.color.color_black));
        middlePadding = getResources().getDimensionPixelSize(R.dimen.padding_value);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();// 整个textView的宽度
    }

    public void setText(JSONArray array) {
        this.array = array;
        if (array != null) {
            invalidate();
        }
    }

    boolean setHeight = false;

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        int lineCount = 0;
        int size = array.length();
        float maxLeftWidth = 0;
        float drawWidth = 0;
        try {
            for (int i = 0; i < size; ++i) {
                JSONArray o = (JSONArray) array.get(i);
                String key = o.getString(0);
                float v = paint1.measureText(key);
                if (v > maxLeftWidth) {
                    maxLeftWidth = v;
                }
            }
            maxLeftWidth = maxLeftWidth + middlePadding;
            for (int i = 0; i < size; ++i) {
                JSONArray o = (JSONArray) array.get(i);
                String key = o.getString(0);
                canvas.drawText(key, 0, (lineCount + 1) * textSize, paint1);
                String value = o.getString(1);
                char[] textCharArray = value.toCharArray();
                float charWidth;
                drawWidth = maxLeftWidth;
                for (int j = 0; j < textCharArray.length; j++) {
                    charWidth = paint1.measureText(textCharArray, j, 1);
                    if (width - drawWidth < charWidth) {
                        lineCount++;
                        drawWidth = maxLeftWidth;
                    }
                    canvas.drawText(textCharArray, j, 1, drawWidth, (lineCount + 1) * textSize, paint2);
                    drawWidth += charWidth;
                }
                lineCount += 2;
            }
            if (!setHeight) {
                setHeight((lineCount + 1) * (int) textSize + 5);
                setHeight = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
