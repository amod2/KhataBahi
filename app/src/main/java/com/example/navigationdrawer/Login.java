package com.example.navigationdrawer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LogIn(View view) {
        EditText e_pass=findViewById(R.id.password);
        if(e_pass.getText().toString().equals("saroj94206")){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
            e_pass.setText("");
        }

    }

    public void signup_clk(View view) {
        Intent intent=new Intent(getApplicationContext(),sign_up.class);
        startActivity(intent);
    }
}