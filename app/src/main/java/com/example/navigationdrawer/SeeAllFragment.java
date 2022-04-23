package com.example.navigationdrawer;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class SeeAllFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    ArrayList<Customer> data;
    recycleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view=inflater.inflate(R.layout.fragment_see_all, container, false);
        recyclerView=view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        data=new ArrayList<>();
        database db=new database(getContext());
        Cursor cr=db.getCursorData();
        while (cr.moveToNext()){
            data.add(new Customer(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5),cr.getString(6),
                    cr.getString(7),cr.getString(8),cr.getBlob(9)));
        }
        if(data.size()>0){
            adapter=new recycleAdapter(getContext(),data);
            recyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(getContext(), "Udhari data not added yet", Toast.LENGTH_SHORT).show();
        }

        return view;
    }


    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.recycle_menu,menu);
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
        super.onCreateOptionsMenu(menu, inflater);

    }
}