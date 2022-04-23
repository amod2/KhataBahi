package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhotoView extends AppCompatActivity {
int id;
    ArrayList<Customer> cus;
    byte [] profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        id= Integer.parseInt(getIntent().getExtras().getString("id"));

        cus=recycleAdapter.getCustomers();
        for(int i=0;i<cus.size();i++){
            if(cus.get(i).getId() ==id){
                profile=cus.get(i).getImgByte();
                break;
            }
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(profile,0,profile.length);
        if(bitmap !=null){
            ImageView imageView=findViewById(R.id.imageViewId);
            imageView.setImageBitmap(bitmap);
        }



    }

    public void backClick(View view) {
        Intent intent=new Intent(getApplicationContext(),udhar_info.class);
        intent.putExtra("key",String.valueOf(id));
        startActivity(intent);
    }
}