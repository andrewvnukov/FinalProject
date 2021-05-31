package com.example.finalproject;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegisterActivity extends AppCompatActivity {
    Button bt1;
    EditText txt1, txt2, txt3;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @SuppressLint("SetTextI18n")
    private void displayDatabaseInfo(){

        // Зададим условие для выборки - список столбцов
        String[] projection = {
                DatabaseHelper.TABLE,
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_PASS};

        // Делаем запрос
        // таблица
        // столбцы
        // столбцы для условия WHERE
        // значения для условия WHERE
        // Don't group the rows
        // Don't filter by row groups
        // порядок сортировки

        try (Cursor cursor = db.query(
                DatabaseHelper.TABLE,   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null)) {
            TextView displayTextView = findViewById(R.id.textView3);
            displayTextView.setText("Таблица содержит " + cursor.getCount() + " гостей.\n\n");
            displayTextView.append(DatabaseHelper.TABLE + " - " +
                    DatabaseHelper.COLUMN_NAME + " - " +
                    DatabaseHelper.COLUMN_PASS);

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor.getColumnIndex(DatabaseHelper.TABLE);
            int nameColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
            int passColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PASS);
            // Проходим через все ряды
            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPass = cursor.getInt(passColumnIndex);
                // Выводим значения каждого столбца
                displayTextView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPass + " - "));
            }
        }
        // Всегда закрываем курсор после чтения
    }
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


        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = txt2.toString();
                databaseHelper.create_db();
                databaseHelper.open();
                displayDatabaseInfo();
            }
        });
    }
}



