package com.anthony.moneylender.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.anthony.moneylender.R;
import com.anthony.moneylender.ui.login.LoginActivity;

import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {

    private LottieAnimationView animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //PRIMER LAYOUT INFLADO CON UNA ANIMACIÓN LOTTI
            animation = findViewById(R.id.mySplash);
            startAnimation();

    }

    private void startAnimation() {
//        FUNCION QUE INSTANCIA EL JSON DE RAW CON LA ANIMACION
        animation.setAnimation(R.raw.workinganimation);
        animation.playAnimation();
        Intent intent = new Intent(this,LoginActivity.class);
        //EVENTO AL FINALIZAR LA ANIMACION
        animation.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            startActivity(intent);
            finish();

            }
        });

    }
}