package com.example.mohamed_nabil.toa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
AutoCompleteTextView au;
    EditText num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.doaa:
                        Intent intent = new Intent(MainActivity.this,Doaa.class);
                        startActivity(intent);
                        break;
                    case R.id.sebha:

                        break;
                    case R.id.doaafav:
                        Intent i = new Intent(MainActivity.this,Doaa_Fav.class);
                        startActivity(i);
                        break;

                }
                return true;
            }
        }

        );
         num = (EditText)findViewById(R.id.editText);
        String[] ss = {"استغفر الله العظيم","سبحان الله","الحمدلله","لا إله إلا الله"};

au = (AutoCompleteTextView)findViewById(R.id.AutoCom);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,ss);
        au.setAdapter(adapter);

    }
    public void start(View view)
    {
        String txt1String = num.getText().toString();
        String name = au.getText().toString();
     if (txt1String.matches("")){
         Toast.makeText(MainActivity.this,"يجب ملئ جميع البيانات",Toast.LENGTH_LONG).show();
     return;}
     else if ( name.matches(""))
     {
         Toast.makeText(MainActivity.this,"يجب ملئ جميع البيانات",Toast.LENGTH_LONG).show();
         return;
     }
     else if(isNumeric(name)== true){
         Toast.makeText(MainActivity.this,"يجب إدخال أسم للذكر صحيح",Toast.LENGTH_LONG).show();
     }
      else{
         Intent intent = new Intent(MainActivity.this,Tasbeh.class);
        intent.putExtra("name",name);
        intent.putExtra("num",txt1String);
        startActivity(intent);}
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(.\\d+)?");
    }

    @Override
    public void onBackPressed() {
        Intent ss = new Intent(MainActivity.this,Doaa.class);
        startActivity(ss);
        super.onBackPressed();
    }

}
