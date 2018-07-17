package com.example.mohamed_nabil.toa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class backend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend);




        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                      sleep(4000);
                    Intent intent = new Intent(getApplicationContext(),Doaa.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
        };
       thread.start();
    }
}
