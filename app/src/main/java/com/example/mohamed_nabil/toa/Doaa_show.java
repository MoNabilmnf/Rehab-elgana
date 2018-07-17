package com.example.mohamed_nabil.toa;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class Doaa_show extends AppCompatActivity {
DB_Sql db = new DB_Sql(this);
    String namepage;
    int id;
Menu menu;
    items it = new items();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doaa_show);
        WebView webView = (WebView)findViewById(R.id.webView);
        TextView textView = (TextView)findViewById(R.id.textView);
        Intent data = getIntent();
         id = data.getExtras().getInt("page");

        /*Cursor code = db.getitemsids(namepage);
        code.moveToFirst();
        String test= code.getString(0);*/
        String [] z = it.arr;
        namepage = z[id];
        textView.setText(namepage);


        webView.loadUrl("file:///android_asset/doaa/"+id+".html");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.fav,menu);
        int cid = db.checkfav(namepage);
        if(cid > 0) {
            menu.getItem(0).setVisible(false);

        }
        else
        {
            menu.getItem(1).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.favon:


                String ids = String.valueOf(id);
                Integer ress = db.DeleteId(ids);
                if(ress > 0){
                    menu.getItem(1).setVisible(false);
                    menu.getItem(0).setVisible(true);
                    Toast.makeText(Doaa_show.this,"تم إلغاء هذا الدعاء من المفضل",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(Doaa_show.this,"no",Toast.LENGTH_LONG).show();


                return true;
            case R.id.favoff:
                boolean res = db.insert(id,namepage);
                if (res == true){
                    menu.getItem(0).setVisible(false);
                    menu.getItem(1).setVisible(true);
                    Toast.makeText(Doaa_show.this,"تم أضافة الدعاء الى المفضله",Toast.LENGTH_LONG).show();}
                else
                    Toast.makeText(Doaa_show.this,"لم يتم الاضافه",Toast.LENGTH_LONG).show();


                return true;
            case R.id.back:
                Intent ss = new Intent(Doaa_show.this,Doaa.class);
                startActivity(ss);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        Intent ss = new Intent(Doaa_show.this,Doaa.class);
        startActivity(ss);
        super.onBackPressed();
    }
}
