package com.test.ebahi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.navigationdrawer.R;

import java.util.ArrayList;

public class paid extends AppCompatActivity {
    ArrayList<Customer> data;
    RecyclerView recyclerView;
    recycleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid);

        recyclerView=findViewById(R.id.recycle3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        data=new ArrayList<>();
        database db=new database(getApplicationContext());
        Cursor cr=db.getCursorData();
        while (cr.moveToNext()){
            if(cr.getString(6).equals("paid")){
                data.add(new Customer(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5),cr.getString(6),
                        cr.getString(7),cr.getString(8),cr.getBlob(9)));
            }

        }
        if(data.size()>0){
            adapter=new recycleAdapter(getApplicationContext(),data);
            recyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(this, "No One have paid yet ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycle_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }


}
