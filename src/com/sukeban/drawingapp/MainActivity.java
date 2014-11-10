package com.sukeban.drawingapp;

import com.sukeban.drawingapp.ColorPickerView.PaintSelectorListener;
import com.sukeban.drawingapp.BrushPickerFragment.BrushSizeSelectorListener;
import com.sukeban.drawingapp.ClearDrawingConfirmationFragment.ClearDrawingConfirmationListener;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends android.support.v4.app.FragmentActivity {

    private ColorPickerView colorPicker;
    private SimpleDrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = (SimpleDrawingView)findViewById(R.id.simpleDrawingView1);

        colorPicker = (ColorPickerView)findViewById(R.id.colorPickerView1);
        colorPicker.setCustomObjectListener(new PaintSelectorListener() {

            @Override
            public void onItemSelected(int color) {
                drawingView.setDrawPaint(color);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onAddItem(MenuItem m) {
        FragmentManager fm = getSupportFragmentManager();
        final ClearDrawingConfirmationFragment confirmation = new ClearDrawingConfirmationFragment();
        confirmation.setCustomObjectListener(new ClearDrawingConfirmationListener() {

            @Override
            public void onClearSelected() {
                drawingView.clear();
            }
        });
        confirmation.show(fm, "Clear Drawing");
    }

    public void onSaveItem(MenuItem m) {
        // TODO: save the view as a bitmap to the photo roll

    }

    public void onChangeBrush(MenuItem m) {
        FragmentManager fm = getSupportFragmentManager();
        final BrushPickerFragment brushPicker = BrushPickerFragment.newInstance("Brush Size");
        brushPicker.setCustomObjectListener(new BrushSizeSelectorListener() {

            @Override
            public void onItemSelected(int radius) {
                drawingView.setRadius(radius);
                brushPicker.dismiss();
            }
        });
        brushPicker.show(fm, "fragment_brush_picker");
    }
}
