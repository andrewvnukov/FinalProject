package com.example.finalproject;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.theme.MaterialComponentsViewInflater;

import java.util.Random;


public class RegisterActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "name";
    public static final String APP_PREFERENCES_PASSWORD = "password";
    public static final String APP_PREFERENCES_CODE = "secretCode";
    SharedPreferences.Editor editor;
    Button bt1, bt2;
    EditText etxt1, etxt2, etxt3;
    TextInputLayout edtxt1, edtxt2, edtxt3;
    TextView txt1;
    SharedPreferences saver;


    //TODO процесс регистрации
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        bt1 = findViewById(R.id.button2);
        bt2 = findViewById(R.id.button4);
        etxt1 = findViewById(R.id.nickName1);
        etxt2 = findViewById(R.id.password1);
        etxt3 = findViewById(R.id.repPassword1);
        edtxt1 = findViewById(R.id.nickName);
        edtxt2 = findViewById(R.id.password);
        edtxt3 = findViewById(R.id.repPassword);
        txt1 = findViewById(R.id.textView3);
        txt1.setVisibility(View.GONE);

        saver = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        final Random random = new Random();
        final Boolean bool;

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
                onStop();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((etxt3.getText().toString().trim().equals(""))) {
                    TextInputLayout edNameLayout = (TextInputLayout) findViewById(R.id.repPassword);
                    edNameLayout.setError(getResources().getString(R.string.Error));
                    if ((etxt2.getText().toString().trim().equals(""))) {
                        edNameLayout = (TextInputLayout) findViewById(R.id.password);
                        edNameLayout.setError(getResources().getString(R.string.Error));
                    }
                    if ((etxt1.getText().toString().trim().equals(""))) {
                        edNameLayout = (TextInputLayout) findViewById(R.id.nickName);
                        edNameLayout.setError(getResources().getString(R.string.Error));
                    } }else {
                        if ((etxt2.getText().toString().trim().equals(""))) {
                            TextInputLayout edNameLayout = (TextInputLayout) findViewById(R.id.password);
                            edNameLayout.setError(getResources().getString(R.string.Error));
                            if ((etxt3.getText().toString().trim().equals(""))) {
                                edNameLayout = (TextInputLayout) findViewById(R.id.repPassword);
                                edNameLayout.setError(getResources().getString(R.string.Error));
                            }
                            if ((etxt1.getText().toString().trim().equals(""))) {
                                edNameLayout = (TextInputLayout) findViewById(R.id.nickName);
                                edNameLayout.setError(getResources().getString(R.string.Error));
                            }
                        } else {
                            if ((etxt1.getText().toString().trim().equals(""))) {
                                TextInputLayout edNameLayout = (TextInputLayout) findViewById(R.id.nickName);
                                edNameLayout.setError(getResources().getString(R.string.Error));
                             if ((etxt2.getText().toString().trim().equals(""))) {
                                    edNameLayout = (TextInputLayout) findViewById(R.id.password);
                                    edNameLayout.setError(getResources().getString(R.string.Error));
                                }
                                if ((etxt1.getText().toString().trim().equals(""))) {
                                    edNameLayout = (TextInputLayout) findViewById(R.id.nickName);
                                    edNameLayout.setError(getResources().getString(R.string.Error));
                                }
                            } else {
                                editor = saver.edit();
                                final StringBuilder secretCode = new StringBuilder();
                                String alphabet = "abcdefghijklmnopqrstuvwxyz";
                                int password = Integer.parseInt(etxt2.getText().toString());
                                int password1 = Integer.parseInt(etxt3.getText().toString());
                                if (password == password1) {
                                    for (int i = 0; i < 8; i++) {
                                        if (random.nextBoolean())
                                            secretCode.append(alphabet.charAt(random.nextInt(9)));else
                                            secretCode.append(random.nextInt(9)); }
                                    editor.putString(APP_PREFERENCES_NAME, etxt1.getText().toString());
                                    editor.putInt(APP_PREFERENCES_PASSWORD, password);
                                    editor.putString(APP_PREFERENCES_CODE, String.valueOf(secretCode));
                                    editor.apply();
                                    bt1.setVisibility(View.GONE);
                                    bt2.setVisibility(View.GONE);
                                    edtxt1.setVisibility(View.GONE);
                                    edtxt2.setVisibility(View.GONE);
                                    edtxt3.setVisibility(View.GONE);
                                    txt1.setVisibility(View.VISIBLE);
                                    txt1.setText("Вы успешно зарегистрировались!");
                                    txt1.setOnClickListener(new View.OnClickListener() {
                                        @SuppressLint("SetTextI18n")
                                        @Override
                                        public void onClick(View v) {
                                            txt1.setText("Запомните или запишите этот код на случай если забудете пароль:" + secretCode);
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

                        }
                    }
                }
            });

        }
    }



