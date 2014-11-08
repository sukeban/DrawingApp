package com.sukeban.drawingapp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

//https://yahoo.jiveon.com/docs/DOC-6520
//http://guides.codepath.com/android/Basic-Painting-with-Views
//http://guides.codepath.com/android/Defining-Custom-Views

public class SimpleDrawingView extends View {

    // TODO: persist path array for orientation change

    private ArrayList<PathPaintObject> paintedPaths;

    int defaultColor = Color.BLACK;
    int defaultRadius = 5;

    int currentColor = defaultColor;
    int currentRadius = defaultRadius;

    private Paint currentPaint;
    private Path currentPath;

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

    private Paint defaultPaint(){
        Paint drawPaint = new Paint();
        drawPaint.setColor(defaultColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(defaultRadius);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        return drawPaint;
    }

    private void setupPaint() {
        currentPaint = defaultPaint();
        paintedPaths = new ArrayList<PathPaintObject>();
    }

    private Paint newPaintWithColor(int color){
        Paint drawPaint = defaultPaint();
        drawPaint.setColor(color);
        currentColor = color;
        drawPaint.setStrokeWidth(currentRadius);
        return drawPaint;
    }

    private Paint newPaintWithStroke(int stroke){
        Paint drawPaint = defaultPaint();
        drawPaint.setColor(currentColor);
        drawPaint.setStrokeWidth(stroke);
        currentRadius = stroke;
        return drawPaint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int p = 0; p < paintedPaths.size(); p++){
            PathPaintObject ppath= paintedPaths.get(p);
            canvas.drawPath(ppath.getPath(), ppath.getPaint());
        }
    }

    // Get x and y and append them to the path
    public boolean onTouchEvent(MotionEvent event) {

        float pointX = event.getX();
        float pointY = event.getY();
        // Checks for the event that occurs
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            currentPath = new Path();

            PathPaintObject object = new PathPaintObject(currentPath, currentPaint);
            paintedPaths.add(object);

            currentPath.moveTo(pointX, pointY);
            break;
        case MotionEvent.ACTION_MOVE:
            currentPath.lineTo(pointX, pointY);
            break;

        default:
            return false;
       }

       postInvalidate(); // Indicate view should be redrawn
       return true; // Indicate we've consumed the touch and want to keep listening
    }

    public void clear(){
        paintedPaths.clear();
        postInvalidate(); // Indicate view should be redrawn
    }

    public void setDrawPaint(int color){
        currentPaint = newPaintWithColor(color);
    }

    public void setRadius(int radius){
        currentPaint = newPaintWithStroke(radius);
    }
}
