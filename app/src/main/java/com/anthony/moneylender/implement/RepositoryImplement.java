package com.anthony.moneylender.implement;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class RepositoryImplement {
    private final String to;
    private final String mensaje;
    private final int number;
    private ViewGroup containerView;
    private View root;
    private String fragmentContext;
    private Context context;

    public RepositoryImplement(ViewGroup containerView, View root, String fragmentContext, Context context, String to, String mensaje, int number) {
        this.containerView = containerView;
        this.root = root;
        this.fragmentContext = fragmentContext;
        this.context = context;
        this.to = to;
        this.mensaje = mensaje;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getTo() {
        return to;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFragmentContext() {
        return fragmentContext;
    }

    public Context getContext() {
        return context;
    }

    public ViewGroup getContainerView() {
        return containerView;
    }

    public View getRoot() {
        return root;
    }
}
