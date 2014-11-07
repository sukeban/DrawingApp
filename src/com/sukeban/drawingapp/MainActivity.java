package com.sukeban.drawingapp;

import com.sukeban.drawingapp.ColorPickerView.MyCustomObjectListener;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;

public class MainActivity extends Activity {

    private ColorPickerView colorPicker;
    private SimpleDrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = (SimpleDrawingView)findViewById(R.id.simpleDrawingView1);

        colorPicker = (ColorPickerView)findViewById(R.id.colorPickerView1);
        colorPicker.setCustomObjectListener(new MyCustomObjectListener() {

            @Override
            public void onItemSelected(Paint color) {
                drawingView.setDrawPaint(color);
            }
        });

    }
}
