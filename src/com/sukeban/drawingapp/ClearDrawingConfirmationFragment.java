package com.sukeban.drawingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;

public class ClearDrawingConfirmationFragment extends DialogFragment {

    public interface ClearDrawingConfirmationListener {
        public void onClearSelected();
    }

    private ClearDrawingConfirmationListener listener;

    public void setCustomObjectListener(ClearDrawingConfirmationListener listener) {
        this.listener = listener;
    }

    public ClearDrawingConfirmationFragment() {
        this.listener = null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Clear drawing?");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClearSelected();
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return alertDialogBuilder.create();
    }
}
