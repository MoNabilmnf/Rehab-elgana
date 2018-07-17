package com.example.mohamed_nabil.toa;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class Doaa extends AppCompatActivity {
    DB_Sql db = new DB_Sql(this);
    items it = new items();
    private DrawerLayout D;
    String [] items;
    private ActionBarDrawerToggle ABD;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doaa);

       ListView listView = (ListView)findViewById(R.id.listView);
        //ArrayList<String> arrayList = db.getitemss();
          items = it.arr;




        //String ids = String.valueOf(x);
        //Toast.makeText(Doaa.this,ids,Toast.LENGTH_LONG).show();
         arrayAdapter = new ArrayAdapter<>(Doaa.this,R.layout.doaa_ly,R.id.textView_doaa_ly,items);
        listView.setAdapter(arrayAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView names = (TextView) view.findViewById(R.id.textView_doaa_ly);
                String namepage = names.getText().toString().trim();
                int x = find(items,namepage);
                Intent intent = new Intent(Doaa.this,Doaa_show.class);
                
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

                        break;
                    case R.id.sebha:
                        Intent intent = new Intent(Doaa.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.doaafav:
                        Intent i = new Intent(Doaa.this,Doaa_Fav.class);
                        startActivity(i);
                        break;

                }
                return true;
            }
        }

        );


    }

    public static int find (String[] array , String name) {
        return Arrays.asList(array).indexOf(name);
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
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)


                .setMessage("هل تريد الخروج من البرنامج ؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ActivityCompat.finishAffinity(Doaa.this);
                    }

                })
                .setNegativeButton("لا", null)
                .show();
        //super.onBackPressed();


    }




}
