package com.example.sujanhasan.listviewdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    private ListView listView;
    private String[] period;
    private MyDatabaseHelper myDatabaseHelper;
    private ArrayList<String> courceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        listView=findViewById(R.id.showDataListViewId);
        period=getResources().getStringArray(R.array.period_details);
        myDatabaseHelper=new MyDatabaseHelper(this);
        loadData();


    }

    public void loadData(){
        ArrayList<String>listData=new ArrayList<>();
        String dayName = null;
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            dayName=bundle.getString("Day");
        }

        Cursor cursor=myDatabaseHelper.displayAllData(dayName);

        if(cursor.getCount()<=0){
            Toast.makeText(getApplicationContext(),"No data available data in database",Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                listData.add(cursor.getString(0));
                listData.add(cursor.getString(1));
                listData.add(cursor.getString(2));
                listData.add(cursor.getString(3));
                listData.add(cursor.getString(4));
                listData.add(cursor.getString(5));
                listData.add(cursor.getString(6));
            }
            CustomAdapter adapter=new CustomAdapter(this,period,listData);
            listView.setAdapter(adapter);
        }

    }
}
