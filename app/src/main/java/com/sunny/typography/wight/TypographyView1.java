package com.sunny.typography.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sunny.typography.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 计算出左边最长的显示字符串maxLeftWidth，之后draw每一行字符，右边的描述从maxLeftWidth开始draw
 * 当一行显示不完全时，折行并且空出maxLeftWidth的空格长度
 */
public class TypographyView1 extends TextView {

    private Paint leftPaint = new Paint();
    private Paint rightPaint = new Paint();
    private int fullWidth;
    private float textSize;
    private JSONArray array;
    private int middlePadding = 0;
    float maxLeftWidth = 0;
    int itemSize = 0;

    public TypographyView1(Context context) {
        super(context);
        init();
    }

    public TypographyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TypographyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
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
        fullWidth = getWidth();// 整个textView的宽度
    }

    public void setText(JSONArray array) {
        this.array = array;
        if (array != null) {
            try {
                int size = itemSize = array.length();
                for (int i = 0; i < size; ++i) {
                    JSONArray o = (JSONArray) array.get(i);
                    String key = o.getString(0);
                    String value = o.getString(1);
                    if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                        itemSize--;
                        continue;
                    }
                    float curWidth = leftPaint.measureText(key);
                    if (curWidth > maxLeftWidth) {
                        maxLeftWidth = curWidth;
                    }
                }
                maxLeftWidth = maxLeftWidth + middlePadding;
                invalidate();
            } catch (Exception e) {

            }
        }
    }

    boolean setHeight = false;

    @Override
    protected void onDraw(Canvas canvas) {
        if (array == null) {
            return;
        }
        int lineCount = 0;
        try {
            JSONArray item;
            float offsetY;
            for (int i = 0; i < itemSize; ++i) {
                item = (JSONArray) array.get(i);
                offsetY = (lineCount + 1) * textSize;
                canvas.drawText(item.getString(0), 0, offsetY, leftPaint);

                String value = item.getString(1);
                float valueWidth = rightPaint.measureText(value);
                if (valueWidth > fullWidth - maxLeftWidth) {// 一行显示不完
                    char[] textCharArray = value.toCharArray();
                    float charWidth;
                    float drawWidth = maxLeftWidth;
                    for (int j = 0; j < textCharArray.length; j++) {
                        charWidth = rightPaint.measureText(textCharArray, j, 1);
                        if (fullWidth - drawWidth < charWidth) {
                            lineCount++;
                            drawWidth = maxLeftWidth;
                            offsetY += textSize;
                        }
                        canvas.drawText(textCharArray, j, 1, drawWidth, offsetY, rightPaint);
                        drawWidth += charWidth;
                    }
                } else {
                    canvas.drawText(value, maxLeftWidth, offsetY, rightPaint);
                }
                lineCount += 2;
            }
            if (!setHeight) {
                setHeight((lineCount + 1) * (int) textSize);
                setHeight = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
