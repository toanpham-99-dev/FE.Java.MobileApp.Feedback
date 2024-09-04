package com.example.feedbackapp.ui.joinmodule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.fragment.DialogFragmentNavigator;

import com.example.feedbackapp.R;

public class JoinModuleDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //hiện dialog login failed
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.login_failed_dialog, null);
        final Button btnYes = (Button) alertLayout.findViewById(R.id.btn_Yes);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return super.onCreateDialog(savedInstanceState);
      /*  return new AlertDialog.Builder(getActivity())
                .setTitle("Title")
                .setMessage("Sure you wanna do this!")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing (will close dialog)
                    }
                })
                .setPositiveButton(android.R.string.yes,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do something
                    }
                })
                .create();
    }
*/
    }
    }
