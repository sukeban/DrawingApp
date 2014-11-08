package com.sukeban.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BrushPickerView extends View {

    private Paint paint;

    public BrushPickerView(Context context) {
        super(context);
    }

    public BrushPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    public BrushPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int radius1 = 5;
        int radius2 = 10;
        int radius3 = 20;

        int originX = width/2;
        int originY = height/2;
        canvas.drawCircle(originX, originY, radius2, paint);

        originY -= radius2;
        originY -= 20;
        originY -= radius1;
        canvas.drawCircle(originX, originY, radius1, paint);

        originY = height/2;
        originY += radius2;
        originY += 20;
        originY += radius3;
        canvas.drawCircle(originX, originY, radius3, paint);
    }

    // TODO: onDraw and intercept touch events and send a notification
    // you could make this a table or some buttons to get touch feedback

}
