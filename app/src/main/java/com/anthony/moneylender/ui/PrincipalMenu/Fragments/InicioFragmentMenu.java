package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import static com.anthony.moneylender.implement.EncoderHelperImplement.decode;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anthony.moneylender.R;
import com.anthony.moneylender.implement.SerializableUserImplement;

public class InicioFragmentMenu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InicioFragmentMenu() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static InicioFragmentMenu newInstance(String param1, String param2) {
        InicioFragmentMenu fragment = new InicioFragmentMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inicio_menu, container, false);
        SerializableUserImplement administrator = (SerializableUserImplement) getArguments().getSerializable("INFORMATION");
        if(administrator.getPhotoUser()!= null){
            Bitmap p = decode(administrator.getPhotoUser());

            ImageView photo= root.findViewById(R.id.photoUserSingin);
            photo.setImageBitmap(p);
        }

        TextView e = root.findViewById(R.id.nameUserSingin);
        e.setText(administrator.getNameUser());
        return root;
    }
}