package com.sukeban.drawingapp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ColorPickerView extends View {

    private int shapeWidth = 40;
    private int shapeHeight = 40;
    private int numColorsInRow = 6;

    private ArrayList<Paint> paintColors;

    private void setUpPaints(){

        paintColors = new ArrayList<Paint>();

        Paint black = new Paint();
        black.setStyle(Style.FILL);
        black.setColor(Color.BLACK);
        paintColors.add(black);

        Paint darkgray = new Paint();
        darkgray.setStyle(Style.FILL);
        darkgray.setColor(Color.DKGRAY);
        paintColors.add(darkgray);

        Paint gray = new Paint();
        gray.setStyle(Style.FILL);
        gray.setColor(Color.GRAY);
        paintColors.add(gray);

        Paint lightgray = new Paint();
        lightgray.setStyle(Style.FILL);
        lightgray.setColor(Color.LTGRAY);
        paintColors.add(lightgray);

        Paint white = new Paint();
        white.setStyle(Style.FILL);
        white.setColor(Color.WHITE);
        paintColors.add(white);

        Paint red = new Paint();
        red.setStyle(Style.FILL);
        red.setColor(Color.RED);
        paintColors.add(red);

        Paint green = new Paint();
        green.setStyle(Style.FILL);
        green.setColor(Color.GREEN);
        paintColors.add(green);

        Paint blue = new Paint();
        blue.setStyle(Style.FILL);
        blue.setColor(Color.BLUE);
        paintColors.add(blue);

        Paint yellow = new Paint();
        yellow.setStyle(Style.FILL);
        yellow.setColor(Color.YELLOW);
        paintColors.add(yellow);

        Paint cyan = new Paint();
        cyan.setStyle(Style.FILL);
        cyan.setColor(Color.CYAN);
        paintColors.add(cyan);

        Paint magenta = new Paint();
        magenta.setStyle(Style.FILL);
        magenta.setColor(Color.MAGENTA);
        paintColors.add(magenta);

        for (Paint p : paintColors)
            System.out.println("color x: " + p.getColor());

    }

    public ColorPickerView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        setUpPaints();
    }

    public ColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpPaints();

        // TODO: can you ask what your bounds are to determine this size for each rectangle?
        // yes use the layout listener
    }

    public ColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpPaints();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int numColors = paintColors.size();

        int originY = 0;
        int originX = 0;

        for (int color=0; color<numColors; color++){
            canvas.drawRect(originX, originY, shapeWidth, shapeHeight, paintColors.get(color));

            System.out.println("making rect at x: " + originX + " y:" + originY + " with color:" +  paintColors.get(color).getColor());

            originX += shapeWidth;

            if (color == numColorsInRow-1){
                originX = 0;
                originY += shapeHeight;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        // TODO: figure out which rectangle was tapped and fire a notification


        return false;
    }

}
