package com.test.ebahi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigationdrawer.R;

public class SettingFragment extends Fragment {
View view;
    database db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_setting, container, false);
        db=new database(getContext());
        CardView cardView=view.findViewById(R.id.cardview1);
        cardView.setClickable(true);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        CardView removePaid=view.findViewById(R.id.cardview4);
        removePaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.validation_alert);
                EditText password=dialog.findViewById(R.id.master_key);
                Button success=dialog.findViewById(R.id.verify_btn);
                Button back=dialog.findViewById(R.id.cancel_btn);
                success.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(password.getText().toString().equals("666666")){
                            boolean res= db.deletePaidData("paid");
                            if(res){
                                Toast.makeText(getContext(), "Paid lists are removed successfully", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getContext(),MainActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                            Intent intent=new Intent(getContext(),MainActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "Invalid key", Toast.LENGTH_SHORT).show();
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
        CardView reset=view.findViewById(R.id.cardview5);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.validation_alert);
                EditText password=dialog.findViewById(R.id.master_key);
                Button success=dialog.findViewById(R.id.verify_btn);
                Button back=dialog.findViewById(R.id.cancel_btn);
                success.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(password.getText().toString().equals("666666")){
                            db.deleteAllData();
                            Toast.makeText(getContext(), "Resetting Application", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getContext(),splaceScreen.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "Invalid key", Toast.LENGTH_SHORT).show();
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
        return view;
    }
}