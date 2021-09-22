package com.anthony.moneylender.ui.PrincipalMenu.Fragments;

import static com.anthony.moneylender.implement.EncoderHelperImplement.decode;
import static com.anthony.moneylender.implement.EncoderHelperImplement.encode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.databinding.FragmentPerfilAmdBinding;
import com.anthony.moneylender.implement.MySnackbarImplement;
import com.anthony.moneylender.implement.SecurityPassImplement;
import com.anthony.moneylender.implement.SerializableUserImplement;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class PerfilAmdFragment extends Fragment {

    private FloatingActionButton return_,administra_,about_ ;
    private View root;
    private FragmentPerfilAmdBinding binding;
    private SerializableUserImplement user;
    private String passAdministrator;
    private DataBaseMoney db;
    private String nombre, apellido,email,photo;
    private Bitmap photoBitmap;
    private byte[] pass;
    private String[] parts;
    private SecurityPassImplement security;
    private ActivityResultLauncher<Intent> imagenUp;
    private MySnackbarImplement mySnackbarImplement;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPerfilAmdBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        //datos bitmap seleccionados de la galeria
        imagenUp = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri imageUri = data.getData();
                            final InputStream imageStream;
                            try {
                                imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                                photoBitmap = BitmapFactory.decodeStream(imageStream);
                                photo = encode(photoBitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            binding.photoAdministrator.setImageURI(imageUri);

                        }
                    }
                });



        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //INICIALIZACION DE DATOS Y ARGUMENTOS BUDLE DEL ADMINISTRADOR

        db = DataBaseMoney.getInstance(getContext());
        return_ = view.findViewById(R.id.Fb_returnIcon);
        administra_ = view.findViewById(R.id.Fb_userAdministra);
        about_ = view.findViewById(R.id.Fb_aboutApplication);

        Bundle bundle = getActivity().getIntent().getExtras();

        user = (SerializableUserImplement) bundle.getSerializable("INFORMATION");
        final NavController navController = Navigation.findNavController(view);
        //INSERTE IMAGEN SI EXISTE.

        photo = user.getPhotoUser();

        eventClick(navController);
        setArgumentsHint(user);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loaderImg(ActivityResultLauncher<Intent> imagenUp) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/*");
        imagenUp.launch(gallery);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setArgumentsHint(SerializableUserImplement user) {
        if (photo != null) {
            binding.photoAdministrator.setImageBitmap(decode(user.getPhotoUser()));
        }
        security = new SecurityPassImplement();
        passAdministrator = security.descifra(user.getPass());

        String descriptionPass = "";

        for (int i = 0;i < passAdministrator.length();i++){
            descriptionPass += "*";
        }

        binding.nameAdmin.setHint(user.getNameUser());
        binding.emailAdmin.setHint(user.getEmail());
        binding.passAdmin.setHint(descriptionPass);


    }


    private void eventClick(NavController navController) {

        return_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_perfilAmdFragment_to_inicioFragmentMenu);
            }
        });
        administra_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_perfilAmdFragment_to_historialClientFragment3);
            }
        });
        about_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_perfilAmdFragment_to_acercaFragment);
            }
        });
        binding.photoAdministrator.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                loaderImg(imagenUp);

            }
        });

        binding.btnUpdateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkData();

                if (!binding.nameAdmin.getText().toString().isEmpty() ||
                !binding.emailAdmin.getText().toString().isEmpty() ||
                !binding.passAdmin.getText().toString().isEmpty()){

                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {

                            db.interfaceDao().updateAdministrator(nombre,apellido,email,pass,photo,user.getIdUser());
                        }
                    });
                    mySnackbarImplement = new MySnackbarImplement("Actualización completa",root);

                }else{
                    mySnackbarImplement = new MySnackbarImplement("No ha realizado ningun cambio",root);
                }



            }
        });


    }


    private void checkData() {
        if (binding.nameAdmin.getText().toString().isEmpty()){
            parts = user.getNameUser().split(" ");
            nombre = parts[0];
            apellido = parts[1];
        }else{
            parts = binding.nameAdmin.getText().toString().split(" ");
            nombre = parts[0];
            apellido = parts[1];
        }

        if(binding.emailAdmin.getText().toString().isEmpty()){
        email = user.getEmail();
        }else{
            email = binding.emailAdmin.getText().toString();
        }

        if (binding.passAdmin.getText().toString().isEmpty()){

            pass = user.getPass();
        }else{
            security = new SecurityPassImplement();
            pass = security.cifra(binding.passAdmin.getText().toString());
        }


    }


}