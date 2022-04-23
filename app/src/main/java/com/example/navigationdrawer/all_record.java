package com.example.navigationdrawer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;
public class all_record extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Customer> data;
    recycleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_record);
        recyclerView=findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        data=new ArrayList<>();
        database db=new database(getApplicationContext());
        Cursor cr=db.getCursorData();
        while (cr.moveToNext()){
            data.add(new Customer(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5),cr.getString(6),
                    cr.getString(7),cr.getString(8),cr.getBlob(9)));
        }
        if(data.size()>0){
            adapter=new recycleAdapter(getApplicationContext(),data);
            recyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(this, "Udhari data not added yet", Toast.LENGTH_SHORT).show();
        }
        Button button=findViewById(R.id.action_search);


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