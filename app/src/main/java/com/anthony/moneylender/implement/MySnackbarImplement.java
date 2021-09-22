package com.anthony.moneylender.implement;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MySnackbarImplement {
    Snackbar snackbar;
    String message;
    View viewState;
    public MySnackbarImplement(String message, View context){
        this.viewState = context;
        this.message = message;
        displaySnack();


    }

    public void displaySnack() {
        if (snackbar == null){

            snackbar = Snackbar.make(viewState,message, BaseTransientBottomBar.LENGTH_LONG);
        }
        snackbar.show();
    }
}
