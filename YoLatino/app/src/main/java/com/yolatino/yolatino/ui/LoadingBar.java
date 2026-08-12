package com.yolatino.yolatino.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.yolatino.yolatino.R;

public class LoadingBar {

    private Activity activity;
    private AlertDialog alertDialog;

    public LoadingBar(Activity activity) {
        this.activity = activity;
    }

    public void iniciarDialogCargando(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.loading_bar, null));

        builder.setCancelable(true);

        alertDialog = builder.create();

        alertDialog.show();

    }

    public void cerrarDialogCargando(){

        alertDialog.dismiss();

    }

}
