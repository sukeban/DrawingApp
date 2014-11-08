package com.sukeban.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

//https://yahoo.jiveon.com/docs/DOC-6520
//http://guides.codepath.com/android/Basic-Painting-with-Views
//http://guides.codepath.com/android/Defining-Custom-Views
public class SimpleDrawingView extends View {

    // TODO: persist path array for orientation change

    private final int paintColor = Color.BLACK;
    private Paint drawPaint;

    private Path path = new Path(); // TODO: array of paths each with own paint type

    public SimpleDrawingView(Context context) {
        super(context);
    }

    // without a style, called by inflater passing in attribute set
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

    public void setDrawPaint(Paint paint)
    {
        drawPaint.setColor(paint.getColor()); // TODO: for now just take the color, can also take style
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, drawPaint);
        // TODO: make an array of paths for each color change
    }

    // TODO: detect a long press and then change the brush size

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

    public boolean onLongClick (View v){
        Toast.makeText(getContext(), "long pressed", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void clear(){
        path.reset();
        postInvalidate(); // Indicate view should be redrawn
    }

    public void setRadius(int radius){
        drawPaint.setStrokeWidth(radius);
        postInvalidate(); // Indicate view should be redrawn
    }
}
