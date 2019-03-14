package com.example.sujanhasan.listviewdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateTimeTable extends AppCompatActivity {

    private EditText period0,period1,period2,period3,period4,period5,period6,period7;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_time_table);

        period0=findViewById(R.id.p0UpdateEditText);
        period1=findViewById(R.id.p1UpdateEditText);
        period2=findViewById(R.id.p2UpdateEditText);
        period3=findViewById(R.id.p3UpdateEditText);
        period4=findViewById(R.id.p4UpdateEditText);
        period5=findViewById(R.id.p5UpdateEditText);
        period6=findViewById(R.id.p6UpdateEditText);
        period7=findViewById(R.id.p7UpdateEditText);

        myDatabaseHelper=new MyDatabaseHelper(this);
        loadEditData();

    }

    public void loadEditData(){
        ArrayList<String> listData=new ArrayList<>();
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

        }

        period0.setText(dayName);
        period1.setText(listData.get(0));
        period2.setText(listData.get(1));
        period3.setText(listData.get(2));
        period4.setText(listData.get(3));
        period5.setText(listData.get(4));
        period6.setText(listData.get(5));
        period7.setText(listData.get(6));
    }

    public void updateButtonClick(View view) {
        String Day=period0.getText().toString();
        String p1=period1.getText().toString();
        String p2=period2.getText().toString();
        String p3=period3.getText().toString();
        String p4=period4.getText().toString();
        String p5=period5.getText().toString();
        String p6=period6.getText().toString();
        String p7=period7.getText().toString();

        int rowId=myDatabaseHelper.updateData(Day,p1,p2,p3,p4,p5,p6,p7);
        if(rowId>0){
            Toast.makeText(getApplicationContext(),"Record update successfully",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(UpdateTimeTable.this,ShowDayActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Data can't update successfully",Toast.LENGTH_LONG).show();
        }
    }
}
