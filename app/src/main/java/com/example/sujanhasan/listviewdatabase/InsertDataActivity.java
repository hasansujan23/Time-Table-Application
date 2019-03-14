package com.example.sujanhasan.listviewdatabase;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertDataActivity extends AppCompatActivity {

    private EditText  p1EditText,p2EditText,p3EditText,p4EditText,p5EditText,p6EditText,p7EditText;
    private Spinner spinner;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        myDatabaseHelper =new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();

        spinner=findViewById(R.id.spinnerId);
        p1EditText=findViewById(R.id.p1EditText);
        p2EditText=findViewById(R.id.p2EditText);
        p3EditText=findViewById(R.id.p3EditText);
        p4EditText=findViewById(R.id.p4EditText);
        p5EditText=findViewById(R.id.p5EditText);
        p6EditText=findViewById(R.id.p6EditText);
        p7EditText=findViewById(R.id.p7EditText);


    }

    public void saveButton(View view) {

        String day=spinner.getSelectedItem().toString();
        String p1=p1EditText.getText().toString();
        String p2=p2EditText.getText().toString();
        String p3=p3EditText.getText().toString();
        String p4=p4EditText.getText().toString();
        String p5=p5EditText.getText().toString();
        String p6=p6EditText.getText().toString();
        String p7=p7EditText.getText().toString();

        if(day.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Day name is missing",Toast.LENGTH_LONG).show();
        }else {
            long rowId=myDatabaseHelper.insertData(day,p1,p2,p3,p4,p5,p6,p7);

            if(rowId>0){
                Toast.makeText(InsertDataActivity.this,"Data insert successfully",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(InsertDataActivity.this,"Data can't insert successfully",Toast.LENGTH_LONG).show();
            }

            p1EditText.setText("");
            p2EditText.setText("");
            p3EditText.setText("");
            p4EditText.setText("");
            p5EditText.setText("");
            p6EditText.setText("");
            p7EditText.setText("");
        }


    }
}
