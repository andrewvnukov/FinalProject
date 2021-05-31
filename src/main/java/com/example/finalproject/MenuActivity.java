package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

class Viewer {

}

public class MenuActivity extends AppCompatActivity {

    Button bt1, bt2;
    EditText txt1, txt2;
    TextView txt3;
    Spinner spinner;


    void show (){
        txt1.setVisibility(View.GONE);
        txt2.setVisibility(View.GONE);
        bt1.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        bt2.setVisibility(View.INVISIBLE);
        bt2.setClickable(true);
        txt3.setVisibility(View.VISIBLE);
    }

    //TODO процесс шифрования
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        bt1 = findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button3);
        txt1 = findViewById(R.id.editText1);
        txt2 = findViewById(R.id.editText2);
        txt3 = findViewById(R.id.textView2);
        spinner = findViewById(R.id.spinner);

        txt2.setVisibility(View.GONE);
        bt2.setVisibility(View.GONE);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setVisibility(View.VISIBLE);
                txt2.setVisibility(View.VISIBLE);
                bt1.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.GONE);
                txt3.setVisibility(View.GONE);
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

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
                if (txt1.getText().toString().trim().equals("")) {
                    bt2.setText("Строка пустая, попробуйте еще раз.");

                } else {
                    String selected = spinner.getSelectedItem().toString();
                    StringBuilder string = new StringBuilder();
                    String s1 = txt1.getText().toString();
                    int x1 = s1.trim().length();

                    switch (selected) {
                        case "Шифр Цезаря":
                            txt2.setVisibility(View.VISIBLE);
                            String s2 = txt2.getText().toString();
                            int x2 = Integer.parseInt(s2.trim());
                            String alf = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
                            for (int i = 0; i < x1; i++) {
                                if (s1.charAt(i) == ' ')
                                    string.append(" ");
                                else{
                                for (int j = 0; j < 33; j++) {
                                    if (s1.charAt(i) == alf.charAt(j))
                                        string.append(alf.charAt((j + x2) % 33));
                                }}
                            }
                            show();
                            txt3.setText(string);
                            break;
                        case "Литорея":
                            String consonants = "бвгджзклмнщшчцхфтсрп";
                            String vowels = "аеёиоуыюяьъйэ";
                            for (int i = 0; i < x1; i++) {
                                for (int j = 0; j < 20; j++) {
                                    if (s1.charAt(i) == consonants.charAt(j))
                                        string.append(consonants.charAt((j + 10) % 20));
                                }
                                if (vowels.indexOf(s1.charAt(i)) != -1)
                                    string.append(s1.charAt(i));
                            }
                            show();
                            txt3.setText(string);
                            break;
                    }
                }
            }
        });
    }
}





