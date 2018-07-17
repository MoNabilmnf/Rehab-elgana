package com.example.mohamed_nabil.toa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class Tasbeh extends AppCompatActivity {
    String num,name;
    int id = 0;
    int number;
    TextView t1,t2;
    Animation A;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbeh);
        Intent data = getIntent();
        name = data.getExtras().getString("name");
        num = data.getExtras().getString("num");
         number = Integer.parseInt(num);
        t1 = (TextView)findViewById(R.id.textView2);
        t2 = (TextView)findViewById(R.id.textView3);
        A = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        t1.setText(name);
        t2.setText("0");
    }
    public void onn(View view)
    {

        id++;
        String myString =String.valueOf(id);
        t2.setText(myString);
        //Toast.makeText(Tasbeh.this,"("+id+")"+name,Toast.LENGTH_SHORT).show();
        t1.startAnimation(A);
        t2.startAnimation(A);
        if (id >= number){
            Toast.makeText(Tasbeh.this,"لقد أتممت التسبيح",Toast.LENGTH_SHORT).show();
            Intent J = new Intent(Tasbeh.this,MainActivity.class);
            startActivity(J);
        }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.view,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.tback:
                Intent ss = new Intent(Tasbeh.this,MainActivity.class);
                startActivity(ss);

            default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent ss = new Intent(Tasbeh.this,MainActivity.class);
        startActivity(ss);
        super.onBackPressed();
    }
    }

