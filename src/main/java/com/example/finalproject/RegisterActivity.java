package com.example.finalproject;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class RegisterActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "name";
    public static final String APP_PREFERENCES_PASSWORD = "password";
    public static final String APP_PREFERENCES_CODE = "secretCode";
    SharedPreferences.Editor editor;
    Button bt1;
    EditText etxt1, etxt2, etxt2_1;
    TextView txt1;
    SharedPreferences saver;


    //TODO процесс регистрации
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        bt1 = findViewById(R.id.button2);
        etxt1 = findViewById(R.id.nickName1);
        etxt2 = findViewById(R.id.password);
        etxt2_1 = findViewById(R.id.password2);
        txt1 = findViewById(R.id.textView3);
        txt1.setVisibility(View.GONE);

        saver = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        final Random random = new Random();
        final Boolean bool;

        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editor = saver.edit();
                final StringBuilder secretCode = new StringBuilder();
                String alphabet = "abcdefghijklmnopqrstuvwxyz";
                int password = Integer.parseInt(etxt2.getText().toString());
                int password1 = Integer.parseInt(etxt2_1.getText().toString());
                if (password == password1) {
                    for (int i = 0; i < 8; i++) {
                        if (random.nextBoolean())
                            secretCode.append(alphabet.charAt(random.nextInt(9)));
                        else
                            secretCode.append(random.nextInt(9));
                    }
                    editor.putString(APP_PREFERENCES_NAME, etxt1.getText().toString());
                    editor.putInt(APP_PREFERENCES_PASSWORD, password);
                    editor.putString(APP_PREFERENCES_CODE, String.valueOf(secretCode));
                    editor.apply();
                    bt1.setVisibility(View.GONE);
                    etxt1.setVisibility(View.GONE);
                    etxt2.setVisibility(View.GONE);
                    etxt2_1.setVisibility(View.GONE);
                    txt1.setVisibility(View.VISIBLE);
                    txt1.setText("Вы успешно зарегистрировались!");
                    txt1.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View v) {
                            txt1.setText("Запомните или запишите этот код на случай если забудете пароль:" + secretCode);
                            Toast.makeText(getBaseContext(), saver.getString(APP_PREFERENCES_CODE, ""), Toast.LENGTH_LONG).show();
                            txt1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i;
                                    i = new Intent(RegisterActivity.this, MenuActivity.class);
                                    startActivity(i);
                                    onStop();
                                }
                            });
                        }
                    });


                } else {
                    Toast.makeText(getBaseContext(), "Повторите свой пароль правильно!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}



