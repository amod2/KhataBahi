package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {
database db;
String userid;
SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database db=new database(getApplicationContext());
        if(db.checkShopIsRegistered()){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }else{

        }
        setContentView(R.layout.activity_sign_up);
        database database=new database(getApplicationContext());
        EditText s_name,s_address;
        s_name=findViewById(R.id.shop_name);
        s_address=findViewById(R.id.shop_address);

        findViewById(R.id.add_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               boolean bool= database.insert_shopData(s_name.getText().toString(),s_address.getText().toString(),null);
                if(bool){
                    Toast.makeText(getApplicationContext(), "your shop have been added", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}