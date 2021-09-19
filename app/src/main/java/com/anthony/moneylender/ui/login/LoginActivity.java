package com.anthony.moneylender.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anthony.moneylender.R;
import com.anthony.moneylender.dataAccessRoom.DataBaseMoney;
import com.anthony.moneylender.implement.SecurityPassImplement;
import com.anthony.moneylender.implement.SerializableUserImplement;
import com.anthony.moneylender.ui.PrincipalMenu.PrincipalMenu;
import com.anthony.moneylender.ui.login.optiones.Optiones;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements Serializable {

    private LoginViewModel loginViewModel;
    private DataBaseMoney db;
    private Button loginButton;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        db = DataBaseMoney.getInstance(this);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        loginButton = findViewById(R.id.login);


        loadPreferences(usernameEditText,passwordEditText);



        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());

                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful


            }
        });

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

                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                try {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(),db);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void loadPreferences(EditText usernameEditText, EditText passwordEditText) {

        SharedPreferences sharedPref;

        sharedPref = this.getSharedPreferences("credentiales", Context.MODE_PRIVATE);
        String user = sharedPref.getString("user","No Exist");
        String pass = sharedPref.getString("pass","No Exist");

        if (!user.equals("No Exist") && !pass.equals("No Exist")){
            loginViewModel.login(user,pass,db);
        }

    }


    public void eventOnclick(View view) {
        Intent intent = new Intent(LoginActivity.this, Optiones.class);
        Bundle bundle = new Bundle();


        switch (view.getId()){
            case R.id.singUp:
                bundle.putSerializable("estado","registrar");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.forgotPass:
                bundle.putSerializable("estado","establecer");
                intent.putExtras(bundle);
                finish();
                startActivity(intent);
                break;
        }


    }



    private void updateUiWithUser(LoggedInUserView model) {

        Intent intent = new Intent(LoginActivity.this, PrincipalMenu.class);
        Bundle bundle = new Bundle();
        SerializableUserImplement administrador
                = new SerializableUserImplement(model.getDisplayId(),model.getDisplayName(),
                model.getDisplayPhoto(),model.getDisplayEmail(),model.getDisplayPass());

        bundle.putSerializable("INFORMATION",administrador);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }


}