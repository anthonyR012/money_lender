package com.anthony.moneylender.ui.PrincipalMenu.Fragments.Lender;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anthony.moneylender.R;

import com.getbase.floatingactionbutton.FloatingActionButton;


public class DetailLender extends Fragment {

    private FloatingActionButton return_,administra_,about_ ;
    private View root;

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_detail_lender, container, false);

        return_ = root.findViewById(R.id.Fb_returnIcon);
        administra_ = root.findViewById(R.id.Fb_userAdministra);
        about_ = root.findViewById(R.id.Fb_aboutApplication);
        eventClick();

        return root;
    }

    private void eventClick() {


    }


}