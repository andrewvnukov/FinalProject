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

import java.util.Objects;


//TODO Авторизация

public class AutorizationActivity extends RegisterActivity {
    SharedPreferences saver;
    EditText txt1, txt2;
    TextView txt3;
    Button bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);
        saver = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        txt1 = findViewById(R.id.name);
        txt2 = findViewById(R.id.password);
        txt3 = findViewById(R.id.textView7);
        bt1 = findViewById(R.id.button);
        txt3.setVisibility(View.GONE);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Objects.equals(saver.getString(APP_PREFERENCES_NAME, ""), txt1.getText().toString()) && Objects.equals(saver.getInt(APP_PREFERENCES_PASSWORD, 0), Integer.parseInt(txt2.getText().toString()))){
                    txt1.setVisibility(View.GONE);
                    txt2.setVisibility(View.GONE);
                    bt1.setVisibility(View.GONE);
                    txt3.setVisibility(View.VISIBLE);
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
                    Toast.makeText(getBaseContext(), saver.getString(APP_PREFERENCES_NAME, ""), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}