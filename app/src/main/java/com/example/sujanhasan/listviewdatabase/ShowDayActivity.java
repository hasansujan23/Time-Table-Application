package com.example.sujanhasan.listviewdatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowDayActivity extends AppCompatActivity {

    private ListView dayListView;
    private MyDatabaseHelper myDatabaseHelper;
    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_day);
        dayListView=findViewById(R.id.dayListViewId);
        myDatabaseHelper=new MyDatabaseHelper(this);
        loadDay();
    }

    public void loadDay(){
        ArrayList<String>dayData=new ArrayList<>();
        Cursor cursor=myDatabaseHelper.showAllDay();
        if(cursor.getCount()<=0){
            Toast.makeText(getApplicationContext(),"There are no record in database",Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                dayData.add(cursor.getString(0));
            }
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.day_sample_view,R.id.daySampleTextViewId,dayData);
        dayListView.setAdapter(adapter);

        dayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String value=parent.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(),"Day : "+value,Toast.LENGTH_LONG).show();
               // Intent intent=new Intent(ShowDayActivity.this,ShowDataActivity.class);
               // intent.putExtra("Day",value);
               // startActivity(intent);

                alertDialogBuilder=new AlertDialog.Builder(ShowDayActivity.this);
                alertDialogBuilder.setTitle("Configure Time Table");
                alertDialogBuilder.setIcon(R.drawable.img2);
                alertDialogBuilder.setMessage("Choose an option .....");
                alertDialogBuilder.setPositiveButton("Show", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         Intent intent=new Intent(ShowDayActivity.this,ShowDataActivity.class);
                         intent.putExtra("Day",value);
                         startActivity(intent);
                    }
                });
                alertDialogBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int rowId=myDatabaseHelper.deleteData(value);
                        if(rowId>0){
                            Toast.makeText(getApplicationContext(),value+" delete from time table",Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(getIntent());
                        }else {
                            Toast.makeText(getApplicationContext(),value+" can not delete successfully",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertDialogBuilder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(ShowDayActivity.this,UpdateTimeTable.class);
                        intent.putExtra("Day",value);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}
