package com.example.navigationdrawer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class HomeFragment extends Fragment {
    View view;
    database db;
    int paidCount,unpaidCount,totalCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home, container, false);
        db=new database(getContext());
        TextView unpaidV=view.findViewById(R.id.unpaid_udhari);
        TextView allV=view.findViewById(R.id.total_udhari);
        TextView paidV=view.findViewById(R.id.paid_udhari);
        paidV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),paid.class);
                startActivity(intent);
            }
        });
        allV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),all_record.class);
                startActivity(intent);
            }
        });
        insertAll();
        insertUnpaid();
        insertPaid();
        unpaidV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),unpaid.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void insertPaid() {
        TextView tv=view.findViewById(R.id.paid_udhari);
        TextView tv1=view.findViewById(R.id.paid_udhari2);
        String qry="select amount from udhari_tbl where status='paid'";
        Cursor cr=db.getData(qry);
        int s=0;
        paidCount=0;
        while (cr.moveToNext()){
            s+=cr.getInt(0);
         paidCount++;
        }
        tv.setText(String.valueOf(s));
        tv1.setText(String.valueOf(paidCount));
    }
    private void insertAll(){
        TextView tv=view.findViewById(R.id.total_udhari);
        TextView tv1=view.findViewById(R.id.total_udhari2);
        String qry="select amount from udhari_tbl";
        Cursor cr=db.getData(qry);
        int s=0;
        int sum;
        totalCount=0;
        while (cr.moveToNext()){
            s+=cr.getInt(0);
        totalCount++;
        }
        tv.setText(String.valueOf(s));
        tv1.setText(String.valueOf(totalCount));
    }
    private void insertUnpaid(){
        TextView tv=view.findViewById(R.id.unpaid_udhari);
        TextView tv1=view.findViewById(R.id.unpaid_udhari2);
        String qry="select amount from udhari_tbl where status='unpaid'";
        Cursor cr=db.getData(qry);
        int s=0;
        unpaidCount=0;
        while (cr.moveToNext()){
            s+=cr.getInt(0);
         unpaidCount++;
        }
        tv.setText(String.valueOf(s));
        tv1.setText(String.valueOf(unpaidCount));
    }
}