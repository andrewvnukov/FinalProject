package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MenuActivity extends AppCompatActivity {


    Button bt1;
    TextInputLayout txt1, txt2;
    TextInputEditText etxt1, etxt2;
    TextView txt3;
    Spinner spinner;


    void show() {
        txt1.setVisibility(View.GONE);
        txt2.setVisibility(View.GONE);
        bt1.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        txt3.setVisibility(View.VISIBLE);
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setVisibility(View.VISIBLE);
                bt1.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                txt3.setVisibility(View.GONE);
                String selected = spinner.getSelectedItem().toString();
                switch (selected) {
                    case "Шифр Цезаря":
                        txt2.setVisibility(View.VISIBLE);
                        break;
                    case "Литорея":
                        txt2.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i;
        i = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bt1 = findViewById(R.id.button1);
        txt1 = findViewById(R.id.editText1);
        txt2 = findViewById(R.id.editText2);
        txt3 = findViewById(R.id.textView2);
        etxt1 = findViewById(R.id.editText11);
        etxt2 = findViewById(R.id.editText21);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                String choose = spinner.getSelectedItem().toString();
                if (choose.equals("Шифр Цезаря")) txt2.setVisibility(View.VISIBLE);
                else txt2.setVisibility(View.GONE);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder string = new StringBuilder();
                String selected = spinner.getSelectedItem().toString();
                String s1 = Objects.requireNonNull(etxt1.getText()).toString();
                int x1 = s1.trim().length();
                if (selected.equals("Шифр Цезаря") && (etxt1.getText().toString().trim().equals("")) || Objects.requireNonNull(etxt2.getText()).toString().trim().equals("")) {

                    show();
                    txt3.setText("Строка пустая, попробуйте еще раз.");}
                 else {
                    if (selected.equals("Литорея") && etxt1.getText().toString().trim().equals("")) {
                        show();
                        txt3.setText("Строка пустая, попробуйте еще раз.");}
                    else {



                    switch (selected) {
                        case "Шифр Цезаря":
                            String s2 = Objects.requireNonNull(etxt2.getText()).toString();
                            int x2 = Integer.parseInt(s2.trim());
                            String alf = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
                            for (int i = 0; i < x1; i++) {
                                if (s1.charAt(i) == ' ')
                                    string.append(" ");
                                else {
                                    for (int j = 0; j < 33; j++) {
                                        if (s1.charAt(i) == alf.charAt(j))
                                            string.append(alf.charAt((j + x2 + 33) % 33));
                                    }
                                }
                            }

                            show();
                            txt3.setText(string);
                            s2 = txt3.getText().toString();
                            if (s2.trim().length() != x1)
                                txt3.setText("Этот язык пока что разрабатывается");

                            break;
                       case "Литорея":
                            String consonants = "бвгджзклмнщшчцхфтсрп";
                            String vowels = "аеёиоуыюяьъйэ";
                            for (int i = 0; i < x1; i++) {
                                for (int j = 0; j < 20; j++) {
                                    if (s1.charAt(i) == consonants.charAt(j))
                                        string.append(consonants.charAt((j + 10) % 20)); }
                                if (vowels.indexOf(s1.charAt(i)) != -1)
                                    string.append(s1.charAt(i));
                            }
                            show();
                            s2 = txt3.getText().toString();
                            txt3.setText(string);
                            if (s2.trim().length() != x1) txt3.setText("Этот язык пока что разрабатывается");
                            break;
                    }
                }
            }
        }});
    }
}





