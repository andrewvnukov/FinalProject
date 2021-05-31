package com.example.finalproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class RegisterActivity extends AppCompatActivity {
    Button bt1;
    EditText txt1, txt2, txt3;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

//TODO процесс регистрации
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        bt1 = findViewById(R.id.button2);
        txt1 = findViewById(R.id.textEmail);
        txt2 = findViewById(R.id.nickName1);
        txt3 = findViewById(R.id.password);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();

        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = txt2.toString();
                databaseHelper.open();
                databaseHelper.openUser();
            }
        });{
        }

        }


    }



