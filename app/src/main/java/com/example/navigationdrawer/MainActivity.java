package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
TextView shop,address;
String st1,st2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database db=new database(getApplicationContext());
        Cursor cursor=db.getShopDetails();
        while (cursor.moveToNext()){
            if(cursor.getCount()>0){
                st1=cursor.getString(0);
                st2=cursor.getString(1);
            }
        }

        DrawerLayout drawerLayout=findViewById(R.id.drawer);
        findViewById(R.id.menuImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView=findViewById(R.id.navView);
        NavController navController= Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView,navController);
        View viewHolder=navigationView.getHeaderView(0);
        shop=viewHolder.findViewById(R.id.akm_name);
        address=viewHolder.findViewById(R.id.akm_addr);
        shop.setText(st1);
        address.setText(st2);



    }
}