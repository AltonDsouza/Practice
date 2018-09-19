package com.example.alton.practice;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
DatabaseHelper mydb;
Button b1,b2,b3,b4;
EditText e1,e2,e3,e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.edit1);
        e2=(EditText)findViewById(R.id.edit2);
        e3=(EditText)findViewById(R.id.edit3);
        e4=(EditText)findViewById(R.id.edit4);
        b1=(Button)findViewById(R.id.btn1);
        b2=(Button)findViewById(R.id.btn2);
        b3=(Button)findViewById(R.id.btn3);
        b4=(Button)findViewById(R.id.btn4);
        addData();
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
    }

    public void addData() {
    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean isInserted=mydb.insertData(e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
            if(isInserted){
                Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Not inserted", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }

    @Override
    public void onClick(View view) {
        if(view==findViewById(R.id.btn2)){
       // Cursor c=mydb.getAllData();
            Cursor c=mydb.getAllData();
            //All records will be stored in the cursor object
        if(c.getCount()==0){
            //if no records found
            showMessage("Error","No data found");
        }
        else{
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext()){
                buffer.append("ID: "+c.getString(0)+"\n");
                buffer.append("Name: "+c.getString(1)+"\n");
                buffer.append("Surname: "+c.getString(2)+"\n");
                buffer.append("Marks: "+c.getString(3)+"\n");
            }
            showMessage("Data",buffer.toString());
        }
    }
    if(view==findViewById(R.id.btn3)){
            boolean isUpdate=mydb.updateData(e4.getText().toString(),e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
            if(isUpdate){
                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
    }
    if(view==findViewById(R.id.btn4)){
        boolean deletedrows=mydb.deleteData(e4.getText().toString());
        if(deletedrows){
            Toast.makeText(this, "Delete Successfull", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        //alertDialog.setCancelable(false);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }
}
