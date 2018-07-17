package com.example.mohamed_nabil.toa;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Doaa_Fav extends AppCompatActivity {
    DB_Sql db = new DB_Sql(this);
    int id;
    items it = new items();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doaa__fav);


        ListView listView = (ListView)findViewById(R.id.listView2);
        ArrayList<String> arrayList = db.getitems();


         arrayAdapter = new ArrayAdapter(this,R.layout.doaa_ly,R.id.textView_doaa_ly,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView names = (TextView) view.findViewById(R.id.textView_doaa_ly);
                String n = names.getText().toString().trim();
                String [] items = it.arr;
                int x = find(items,n);
                Intent intent = new Intent(Doaa_Fav.this,Doaa_show.class);

                intent.putExtra("page",x);
                startActivity(intent);


            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.doaa:
                        Intent i = new Intent(Doaa_Fav.this,Doaa.class);
                        startActivity(i);
                        break;
                    case R.id.sebha:
                        Intent intent = new Intent(Doaa_Fav.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.doaafav:
                        break;

                }
                return true;
            }
        }

        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.msearch);
        SearchView searchView = (SearchView)item.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    public static int find (String[] array , String name) {
        return Arrays.asList(array).indexOf(name);
    }
    @Override
    public void onBackPressed() {
        Intent ss = new Intent(Doaa_Fav.this,Doaa.class);
        startActivity(ss);
        super.onBackPressed();
    }
}
