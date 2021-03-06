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

    public interface PaintSelectorListener {
        public void onItemSelected(int color);
    }

    private int shapeWidth = 40;
    private int shapeHeight = 40;
    private int numColorsInRow = 6;
    private int numRows;

    private ArrayList<Paint> paintColors;
    private PaintSelectorListener listener;

    public void setCustomObjectListener(PaintSelectorListener listener) {
        this.listener = listener;
    }

    private void setUpPaints(){

        this.listener = null;

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

        Paint orange = new Paint();
        orange.setStyle(Style.FILL);
        orange.setARGB(255, 255, 189, 136);
        paintColors.add(orange);

        //for (Paint p : paintColors)
        //    System.out.println("color x: " + p.getColor());

        int numColors = paintColors.size();
        numRows = (int) Math.floor(numColors/numColorsInRow);
    }

    public ColorPickerView(Context context) {
        super(context);
        setUpPaints();
    }

    public ColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpPaints();
    }

    public ColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpPaints();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int numColors = paintColors.size();

        int originX = 0;
        int originY = 0;

        float px = getMeasuredWidth()/numColorsInRow;
        float py = getMeasuredHeight()/numRows;

        for (int color=0; color<numColors; color++){
            canvas.drawRect(originX, originY, originX + px, originY + py, paintColors.get(color));

            //System.out.println("making rect at x: " + originX + " y:" + originY + " with color:" +  paintColors.get(color).getColor());

            originX += px;

            if (color == numColorsInRow-1){
                originX = 0;
                originY += py;
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int padding = 10;
        int contentWidth = shapeWidth*numColorsInRow;
        // Resolve the width based on our minimum and the measure spec
        int minw = contentWidth + getPaddingLeft() + getPaddingRight();
        int width = resolveSizeAndState(minw, widthMeasureSpec, 0);
        // Ask for a height that would let the view get as big as it can
        int minh = shapeHeight*numRows + getPaddingBottom() + getPaddingTop() + padding;
        int height = resolveSizeAndState(minh, heightMeasureSpec, 0);
        // Calling this method determines the measured width and height
        // Retrieve with getMeasuredWidth or getMeasuredHeight methods later
        setMeasuredDimension(width, height);
    }

    public boolean onTouchEvent(MotionEvent event) {

        float pointX = event.getX();
        float pointY = event.getY();
        //System.out.println("tapped at x:" + pointX + " y:" + pointY);

        int numColors = paintColors.size();

        float px = getMeasuredWidth()/numColorsInRow;
        float py = getMeasuredHeight()/numRows;

        int column = (int) Math.floor(pointX / px);
        int row = (int) Math.floor(pointY / py);

        int color = Math.min(numColors-1,row*numColorsInRow + column);
        //System.out.println("tapped color:" + paintColors.get(color).getColor());

        Paint paint = paintColors.get(color);
        listener.onItemSelected(paint.getColor());

        return false;
    }

}
