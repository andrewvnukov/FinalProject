package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity {
    Button bt1;
    EditText txt1, txt2;
    TextView txt3;


    //TODO процесс шифрования
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toast.makeText(this, "activity_menu", Toast.LENGTH_SHORT).show();
        bt1 = findViewById(R.id.button1);
        txt1 = findViewById(R.id.editText1);
        txt2 = findViewById(R.id.editText2);
        txt3 = findViewById(R.id.textView2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt1.getText().toString().equals("") || txt2.getText().toString().equals("")) {
                    txt3.setText("Строка пустая, попробуйте еще раз.");

                } else {
                    Spinner spinner = findViewById(R.id.spinner);
                    String selected = spinner.getSelectedItem().toString();
                    StringBuilder string = new StringBuilder();
                    String s1 = txt1.getText().toString();
                    String s2 = txt2.getText().toString();

                    int x1 = s1.trim().length();
                    int x2 = Integer.parseInt(s2.trim());

switch (selected) {
    case "Шифр Цезаря":
        String alf = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        for (int i = 0; i < x1; i++) {

            for (int j = 0; j < 33; j++) {
                if (s1.charAt(i) == alf.charAt(j)) {

                    string.append(alf.charAt((j + x2) % 33));
                }
            }
        }
        txt3.setText(string); break;
    case "Литорея":
        char letter;
        for(int i = 0; i<x1; i++)
            letter = s1.charAt(i);
            switch (letter){
                case 'б': string.append("щ"); break;
                case 'в': string.append("ш"); break;
            }
        break;
}}
            }
        });
    }


}



