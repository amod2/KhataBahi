package com.example.navigationdrawer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class add_udhari extends Fragment {

    String st;
    Button etAdd;
    ImageView set_profile,take_pic;
    Bitmap img;
    public static final int cameraPermCode=101;
    public static final int cameraReqCode=102;
    View view;
    private static final String[] addressArr=new String[]{
            "yadukuha","Nanupati","Dhabauli","Harwada","Hathipur","Hauwahi","nemwatol","Sarabe","Hatletba","Basdepati","Simrapati","Simrari","Sabaila",
            "Kharyani","Gatoli","Gotkuha","Chorakailpur","Pachherba","Bafai","Tinkoriya","Bisharghora","Nathpati","Mangraha","Machi","Lagma","Khaujari",
            "Ekrahi","Gidhha","Mahuliya","Bishargora","Dumariya","Matauna"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view=inflater.inflate(R.layout.fragment_add_udhari, container, false);
        take_pic= view.findViewById(R.id.takepic_btn);
        set_profile= view.findViewById(R.id.imageView11);
        etAdd=view.findViewById(R.id.add_udhari);
       AddressSuggestion();
        take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCamPermission();
            }
        });
        etAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });


        return view;

    }

    private void AddressSuggestion() {
        AutoCompleteTextView address= ( AutoCompleteTextView ) view.findViewById(R.id.address);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,addressArr);
        address.setAdapter(adapter);
        address.setThreshold(1);
    }
    private void addData() {
        EditText et1,et2,et4,et5,et6;
        AutoCompleteTextView et3;
        database db=new database(getContext());
        et1=view.findViewById(R.id.name);
        et2=view.findViewById(R.id.amount);
        et3=view.findViewById(R.id.address);
        et4=view.findViewById(R.id.phone);
        et5=view.findViewById(R.id.date);
        et6=view.findViewById(R.id.description);
        ImageView imageView=view.findViewById(R.id.imageView11);
        String name,address,phone,date,desc;
        int amount=0;
        name=et1.getText().toString();address=et3.getText().toString();phone=et4.getText().toString();
        date=et5.getText().toString();desc=et6.getText().toString();

        try {
            amount=Integer.parseInt(et2.getText().toString());
        }catch (Exception e){
            Toast.makeText(getContext(), "please enter all field", Toast.LENGTH_SHORT).show();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dt1 = new Date();
        st=(String)formatter.format(dt1);
        String status="unpaid";
        if(name.equals("") || address.equals("") || phone.equals("") || date.equals("") || desc.equals("") || et2.getText().toString().equals("")){
            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        }else if(phone.length() >10 || phone.length() <10){
            Toast.makeText(getContext(), "Invalid Phone number", Toast.LENGTH_LONG).show();
        }
        else if(img ==  null) {
            boolean res=db.insert_udhari(name,amount,address,phone,date,status,st,desc,null);
            if(res)
            {     Log.i("akm", "started true");
                Toast.makeText(getContext(), "udhari Inserted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }else {
                Log.i("akm", "addUdhari: started false");
                Toast.makeText(getContext(), "some coding mistakes", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            byte[] profile= BitmapToByteArray(img);
            boolean res=db.insert_udhari(name,amount,address,phone,date,status,st,desc,profile);
            if(res)
            {   Intent intent=new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "udhari Inserted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "some coding mistakes", Toast.LENGTH_SHORT).show();

            }
        }

    }


    private byte[] BitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void askCamPermission() {
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) getContext(),new String[]{Manifest.permission.CAMERA},cameraPermCode);
        }else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == cameraPermCode) {
            if (grantResults.length < 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(getContext(), "camera permission is required to access camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            if(requestCode==cameraReqCode){

                img= (Bitmap) data.getExtras().get("data");
                set_profile.setImageBitmap(img);
            }
        }

    }

    private void openCamera() {
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,cameraReqCode);
    }



}