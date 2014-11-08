package com.sukeban.drawingapp;

import android.graphics.Paint;
import android.graphics.Path;

public class PathPaintObject {

    private Path path;
    private Paint paint;

    public PathPaintObject(Path p, Paint pt) {
        path = p;
        paint = pt;
    }

    public Path getPath(){
        return path;
    }

    public Paint getPaint(){
        return paint;
    }

}
