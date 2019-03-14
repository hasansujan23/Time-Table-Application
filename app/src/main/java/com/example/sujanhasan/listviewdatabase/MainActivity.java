package com.example.sujanhasan.listviewdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.menu_add){
            Intent intent=new Intent(MainActivity.this,InsertDataActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.menu_time_table){
            Intent intent=new Intent(MainActivity.this,ShowDayActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.menu_about){
            Intent intent=new Intent(MainActivity.this,ScrollViewActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
