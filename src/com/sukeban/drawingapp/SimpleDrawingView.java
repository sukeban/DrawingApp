package com.sukeban.drawingapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

//http://guides.codepath.com/android/Basic-Painting-with-Views
public class SimpleDrawingView extends View {

 // setup initial color
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;
    // Store circles to draw each time the user touches down
    //private List<Point> circlePoints;

    private Path path = new Path();

    public SimpleDrawingView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    // without a style, called by iflater passing in attribute set
    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Here load and store attributes for the view
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    // if custom style
    public SimpleDrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    // Setup paint with color and stroke styles
    private void setupPaint() {
      drawPaint = new Paint();
      drawPaint.setColor(paintColor);
      drawPaint.setAntiAlias(true);
      drawPaint.setStrokeWidth(5);
      drawPaint.setStyle(Paint.Style.STROKE);
      drawPaint.setStrokeJoin(Paint.Join.ROUND);
      drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, drawPaint);
    }

    // Get x and y and append them to the path
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        // Checks for the event that occurs
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            // Starts a new line in the path
            path.moveTo(pointX, pointY);
            break;
        case MotionEvent.ACTION_MOVE:
            // Draws line between last point and this point
            path.lineTo(pointX, pointY);
            break;

        default:
            return false;
       }

       postInvalidate(); // Indicate view should be redrawn
       return true; // Indicate we've consumed the touch and want to keep listening
    }

}
