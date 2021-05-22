package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        txt1 =  findViewById(R.id.editText1);
        txt2 =  findViewById(R.id.editText2);
        txt3 = findViewById(R.id.textView2);

    }

    public void onClick(View view) {

        if (txt1.getText().toString().equals("") || txt2.getText().toString().equals("")) {
            txt3.setText("Строка пустая, попробуйте еще раз.");

        } else {
            StringBuilder string = new StringBuilder();
            String s1 = txt1.getText().toString();
            String s2 = txt2.getText().toString();
            int x1 = s1.length();
            int x2 =  Integer.parseInt(s2.trim());
            for (int i = 0; i < x1; i++) {

                for (char j = 'а'; j <= 'я'; j++) {
                    if (s1.charAt(i) ==  j) {
                        string.append((j+x2)%33);
                        txt3.setText(j);
                    }

                }

            }
            txt3.setText(string);
        }
    }


   /*class Kitty extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txt3.setText("Hello");
        }


        @Override
        protected Void doInBackground(Void... voids) {
            String s1 = txt1.getText().toString();
            String s2 = txt2.getText().toString();
            int x1 = s1.length();
            int x2 = s2.length();
            String alphabet = "абвгдеёжзиклмнопрстуфхцчшщъыьэюя";
            for (int i = 1; i <= x1; i++) {

                for (int j = 1; j <= 33; j++) {

                    if (s1.charAt(i) == alphabet.charAt(j)) {
                        j = (j + x2);
                        string.append(alphabet.charAt(j));
                    }

                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txt3.setText(string);
        }

    }*/
}


