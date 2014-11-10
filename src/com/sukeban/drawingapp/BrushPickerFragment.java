package com.sukeban.drawingapp;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BrushPickerFragment extends DialogFragment {

    private Button radius1;
    private Button radius2;
    private Button radius3;

    //private int selectedRadius; // TODO: make current look selected, also pass in the current color

    public interface BrushSizeSelectorListener {
        public void onItemSelected(int radius);
    }

    private BrushSizeSelectorListener listener;

    public void setCustomObjectListener(BrushSizeSelectorListener listener) {
        this.listener = listener;
    }

    public BrushPickerFragment() {
        this.listener = null;
    }

    public static BrushPickerFragment newInstance(String title) {
        BrushPickerFragment frag = new BrushPickerFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brush_picker, container);
        radius1 = (Button) view.findViewById(R.id.radius1);
        radius2 = (Button) view.findViewById(R.id.radius2);
        radius3 = (Button) view.findViewById(R.id.radius3);

        radius1.setOnClickListener(new OnClickListener()
        {
                  @Override
                  public void onClick(View v)
                  {
                      listener.onItemSelected(5);
                  }
        });
        radius2.setOnClickListener(new OnClickListener()
        {
                  @Override
                  public void onClick(View v)
                  {
                      listener.onItemSelected(10);
                  }
        });
        radius3.setOnClickListener(new OnClickListener()
        {
                  @Override
                  public void onClick(View v)
                  {
                      listener.onItemSelected(20);
                  }
        });

        String title = getArguments().getString("title", "Brush Size");
        getDialog().setTitle(title);
        return view;
    }

}
