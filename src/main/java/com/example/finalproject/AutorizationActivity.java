package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.DateTimePatternGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


//TODO Авторизация

public class AutorizationActivity extends RegisterActivity {
    SharedPreferences saver;
    TextInputLayout txt1, txt2;
    TextInputEditText etxt1, etxt2;
    TextView txt3;
    Button bt1, bt2, bt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);
        saver = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        txt1 = findViewById(R.id.nickName);
        txt2 = findViewById(R.id.password);
        etxt1 = findViewById(R.id.nickName1);
        etxt2 = findViewById(R.id.password1);
        txt3 = findViewById(R.id.textView7);
        txt3.setTextColor(Color.parseColor("#C71585"));
        bt1 = findViewById(R.id.button);
        bt2 = findViewById(R.id.button3);
        bt3 = findViewById(R.id.button4);
        txt3.setTranslationY(-500);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(saver.getString(APP_PREFERENCES_NAME, ""), Objects.requireNonNull(etxt1.getText()).toString()) &&
                        Objects.equals(saver.getInt(APP_PREFERENCES_PASSWORD, 0), Integer.parseInt(Objects.requireNonNull(etxt2.getText()).toString()))) {
                    txt1.setVisibility(View.GONE);
                    txt2.setVisibility(View.GONE);
                    bt1.setVisibility(View.GONE);
                    bt2.setVisibility(View.GONE);
                    bt3.setVisibility(View.GONE);
                    txt3.setText("Вы успешно вошли!");
                    txt3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i;
                            i = new Intent(AutorizationActivity.this, MenuActivity.class);
                            startActivity(i);
                            onStop();
                        }
                    });

                } else {
                    Toast.makeText(getBaseContext(), "Неправильное имя пользователя или пароль", Toast.LENGTH_LONG).show();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt2.setVisibility(View.GONE);
                txt1.setTranslationY(300);
                etxt1.setText("");
                txt1.setHint("Код восстановления");
                bt2.setVisibility(View.GONE);

                bt1.setTranslationY(100);
                bt3.setTranslationY(100);
                bt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt2.setVisibility(View.VISIBLE);
                        etxt1.setText("");
                        txt1.setTranslationY(-40);
                        txt1.setHint("Введите никнейм");
                        bt2.setVisibility(View.VISIBLE);
                        bt1.setTranslationY(-100);
                        bt3.setTranslationY(-100);
                        bt1.setText("Авторизация");
                        txt3.setText("Авторизация");
                        bt3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i;
                                i = new Intent(AutorizationActivity.this, MainActivity.class);
                                startActivity(i);
                                onStop();
                            }
                        });
                    }
                });
                bt1.setText("Восстановить доступ");
                txt3.setText("Если вы забыли пароль или имя то введите код, который был выдан после регистрации:");

                bt1.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        if (Objects.requireNonNull(etxt1.getText()).toString().trim().equals(saver.getString(APP_PREFERENCES_CODE, ""))) {
                            txt2.setVisibility(View.GONE);
                            txt1.setVisibility(View.GONE);
                            bt1.setVisibility(View.GONE);
                            bt3.setVisibility(View.GONE);
                            txt3.setTranslationY(100);
                            txt3.setText("Ваш ник:\n" + saver.getString(APP_PREFERENCES_NAME, "")
                                    + "\n\n\nВаш пароль:\n" + saver.getInt(APP_PREFERENCES_PASSWORD, 0));
                            txt3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i;
                                    i = new Intent(AutorizationActivity.this, MenuActivity.class);
                                    startActivity(i);
                                    onStop();
                                }
                            });
                        }
                        else Toast.makeText(getBaseContext(), "Неправильный код восстановления", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(AutorizationActivity.this, MainActivity.class);
                startActivity(i);
                onStop();
            }
        });

    }
}