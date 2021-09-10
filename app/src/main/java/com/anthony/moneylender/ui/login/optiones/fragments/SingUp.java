package com.anthony.moneylender.ui.login.optiones.fragments;

import static com.anthony.moneylender.implement.EncoderHelperImplement.encode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.databinding.FragmentSingUpBinding;
import com.anthony.moneylender.models.login.optiones.SingViewModel;
import com.anthony.moneylender.ui.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingUp extends Fragment {

    private FragmentSingUpBinding binding;
    private SingViewModel viewModel;
    private Context context;
    private DataBaseMoney db;
    private Administrador createAdministrador;
    private Snackbar mySnackbar;
    private Uri imageUri;
    private Bitmap selectedImage;
    private String imageEnconder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewModel = new ViewModelProvider(this).get(SingViewModel.class);
        binding = FragmentSingUpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getActivity().getApplicationContext();
        db = DataBaseMoney.getInstance(context);

        //datos bitmap seleccionados de la galeria

        ActivityResultLauncher<Intent> imagenUp = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            imageUri = data.getData();
                            final InputStream imageStream;
                            try {
                                imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                                selectedImage = BitmapFactory.decodeStream(imageStream);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            binding.photoUser.setImageURI(imageUri);

                        }
                    }
                });


        //funciones encargadas de verificar inputs
        stateLoginUser(root);
        observerLetterEditText();
            root.findViewById(R.id.singIn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivityLogin();


                }
            });

        root.findViewById(R.id.btn_registrar).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                registraUser(root);
            }
        });

        binding.photoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaderImg(imagenUp);
            }
        });

        return root;

    }

    private void loaderImg(ActivityResultLauncher<Intent> imagenUp) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/*");
        imagenUp.launch(gallery);

    }

    private void observerLetterEditText() {
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore

            }

            @Override
            public void afterTextChanged(Editable s) {

                viewModel.loginDataChanged(binding.idUser.getText().toString(),binding.nameUser.getText().toString(),binding.lastNameUser
                        .getText().toString(),binding.emailUser.getText().toString(),binding.passUser.getText().toString(),db);
            }
        };
        binding.idUser.addTextChangedListener(afterTextChangedListener);
        binding.nameUser.addTextChangedListener(afterTextChangedListener);
        binding.lastNameUser.addTextChangedListener(afterTextChangedListener);
        binding.emailUser.addTextChangedListener(afterTextChangedListener);
        binding.passUser.addTextChangedListener(afterTextChangedListener);
    }

    private void stateLoginUser(View root) {
        viewModel.getRegistroFormState().observe( getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer stateRegistro) {
                if (stateRegistro == null) {
                    return;
                }
                if (stateRegistro.equals(R.string.id_exist)) {
                    binding.idUser.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.email_exist)) {
                    binding.emailUser.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.id_invalid)) {
                    binding.idUser.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.nombre_invalid)) {
                    binding.nameUser.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.apellido_invalid)) {
                    binding.lastNameUser.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.email_invalid)) {
                    binding.emailUser.setError(getString(stateRegistro));
                }
                if (stateRegistro.equals(R.string.pass_invalid)) {
                    binding.passUser.setError(getString(stateRegistro));
                }
                if(stateRegistro.equals(R.string.valid_action)){
                    root.findViewById(R.id.btn_registrar).setEnabled(true);

                }

            }
        });
    }

    private void openActivityLogin() {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void registraUser(View root) {

        imageEnconder = selectedImage!=null?encode(selectedImage):null;

        createAdministrador = new Administrador(binding.idUser.getText().toString(),binding.nameUser.getText().toString(),
        binding.lastNameUser.getText().toString(),binding.emailUser.getText().toString(),null,
        imageEnconder);


        int value = viewModel.insertData(createAdministrador,db,binding.passUser.getText().toString());
        mySnackbar = Snackbar.make(root,getString(value), Snackbar.LENGTH_LONG);
        mySnackbar.show();
        cleanEdiText();
    }

    private void cleanEdiText() {
        binding.idUser.setText("");
        binding.nameUser.setText("");
        binding.lastNameUser.setText("");
        binding.emailUser.setText("");
        binding.passUser.setText("");
        binding.photoUser.setImageResource(R.drawable.login);
    }


}