package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class sign_up extends AppCompatActivity {
database db;
String userid;
SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.snp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid=findViewById(R.id.ph).toString();
                db.dbname=userid;
              Intent intent=new Intent(getApplicationContext(),MainActivity.class);
              startActivity(intent);
            }
        });

    }
}