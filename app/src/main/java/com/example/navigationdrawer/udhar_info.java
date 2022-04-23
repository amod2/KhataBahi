package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class udhar_info extends AppCompatActivity {
    database  db=new database(this);
    ArrayList<Customer> cus;
    int id,pos;
    String id1;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udhar_info);
        id= Integer.parseInt(getIntent().getExtras().getString("key"));
        id1=getIntent().getExtras().getString("key");
        cus=recycleAdapter.getCustomers();
        dialog=new Dialog(this);
        getInfo();
        Button btn=findViewById(R.id.payIt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.validation_alert);
                EditText password=dialog.findViewById(R.id.master_key);
                Button success=dialog.findViewById(R.id.verify_btn);
                Button back=dialog.findViewById(R.id.cancel_btn);
                success.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(password.getText().toString().equals("88889999")){
                            boolean res= db.updateStatus(String.valueOf(id));
                            if(res)
                            {   Toast.makeText(udhar_info.this, "Paid Success ", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }else {
                                Toast.makeText(udhar_info.this, "something went wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Invalid key", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });

    }

    private void getInfo() {
        TextView user_name;
        ImageView profile_view;
        TextView user_add;TextView user_phone;TextView user_amount;TextView user_date;TextView user_status;TextView user_desc;
        user_name=findViewById(R.id.user_info_name);
        user_add=findViewById(R.id.user_info_addr);
        user_phone=findViewById(R.id.user_info_phone);
        user_amount=findViewById(R.id.user_info_amount);
        user_date=findViewById(R.id.user_info_date);
        user_status=findViewById(R.id.user_info_status);
        user_desc=findViewById(R.id.user_info_desc);
        profile_view=findViewById(R.id.profile);
        Button button=findViewById(R.id.button6);

        for(int i=0;i<cus.size();i++){
            if(cus.get(i).getId()==id){
                pos=i;
                user_name.setText(cus.get(i).getName());
                user_add.setText(cus.get(i).getAddress());
                user_amount.setText(cus.get(i).getAmount());
                user_phone.setText(cus.get(i).getPhone());
                user_date.setText(cus.get(i).getDate());
                user_desc.setText(cus.get(i).getDescription());
                if(cus.get(i).getImgByte() !=null){
                    Bitmap bitmapImage= BitmapFactory.decodeByteArray(cus.get(i).getImgByte(),0,cus.get(i).getImgByte().length);
                    profile_view.setImageBitmap(bitmapImage);
                }
                if(cus.get(i).getStatus().equals("unpaid")){
                    user_status.setBackgroundColor(R.drawable.unpaid);
                    user_status.setText(cus.get(i).getStatus());

                }else{
                    user_status.setText(cus.get(i).getStatus());
                    user_status.setBackgroundColor(R.drawable.sucsess);
                    Button btn=findViewById(R.id.payIt);
                    btn.setVisibility(View.GONE);
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+cus.get(pos).getPhone()));//change the number
                        if (Build.VERSION.SDK_INT > 23) {
                            startActivity(callIntent);
                        } else {

                            if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(getApplicationContext(), "Permission Not Granted ", Toast.LENGTH_SHORT).show();
                            } else {
                                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                                ActivityCompat.requestPermissions((Activity) getApplicationContext(), PERMISSIONS_STORAGE, 9);
                                startActivity(callIntent);
                            }
                        }
                    }
                });

            }
        }
    }






    public void photoClick(View view) {
        for (int i=0;i<cus.size();i++){
            if(cus.get(i).getId() == id){
                if(cus.get(i).getImgByte() != null){
                    Intent intent=new Intent(getApplicationContext(),PhotoView.class);
                    intent.putExtra("id",id1);
                    startActivity(intent);
                    break;
                }

            }
        }


    }
}