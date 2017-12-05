package com.bugscript.sqlitelight;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;
    Toast to;
    String[] mobileArray;
    String name;
    EditText insertData,deleteDatas;
    ImageView insertChecked,deleteChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertData=findViewById(R.id.insertData);
        deleteDatas=findViewById(R.id.deleteData);
        insertChecked=findViewById(R.id.insertChecked);
        deleteChecked=findViewById(R.id.deleteChecked);

        DBHelperClass dbHelper = new DBHelperClass(this);
        mDb = dbHelper.getWritableDatabase();

        updateList();

        insertChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertIntoTable();
            }
        });

        deleteChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFromTable();
            }
        });



    }

    private void insertIntoTable() {

        String name=insertData.getText().toString().trim().toLowerCase();
        ContentValues cv = new ContentValues();
        cv.put(ContractClass.nameClass.COLUMN_PERSON_NAME, name);
        mDb.insert(ContractClass.nameClass.TABLENAME,null,cv);
        insertData.clearFocus();
        insertData.getText().clear();
        updateList();

    }

    private void deleteFromTable(){
        String nameForDeletion=deleteDatas.getText().toString().trim().toLowerCase();
        try {
            mDb.delete(ContractClass.nameClass.TABLENAME, ContractClass.nameClass.COLUMN_PERSON_NAME + " = '" + nameForDeletion +"'", null);
            Toast.makeText(MainActivity.this, "Deletion performed..", Toast.LENGTH_SHORT).show();
            deleteDatas.clearFocus();
            deleteDatas.getText().clear();
            updateList();
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"No such entry..",Toast.LENGTH_SHORT).show();
            deleteDatas.clearFocus();
            deleteDatas.getText().clear();
            updateList();
        }
//        to delete all entries in the table
//        mDb.delete(ContractClass.nameClass.TABLENAME,null,null);
//        updateList();
    }

    public void updateList(){

        Cursor cursor = getAllGuests();
        mobileArray=new String[cursor.getCount()];

        Toast.makeText(MainActivity.this,"No.of Rows:"+cursor.getCount(),Toast.LENGTH_SHORT).show();

        for(int i=0;i<cursor.getCount();i++){
            cursor.moveToNext();
            name=cursor.getString(cursor.getColumnIndex(ContractClass.nameClass.COLUMN_PERSON_NAME));
            mobileArray[i]=name;
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }


    private Cursor getAllGuests() {
        Cursor sample=mDb.query(
                ContractClass.nameClass.TABLENAME,
                null,
                null,
                null,
                null,
                null,
                ContractClass.nameClass.TIME_STAMP
        );
        if(sample.getCount()>0){
            Toast.makeText(MainActivity.this,"Data fetched successfully..",Toast.LENGTH_SHORT).show();
        }
        return mDb.query(
                ContractClass.nameClass.TABLENAME,
                null,
                null,
                null,
                null,
                null,
                ContractClass.nameClass.TIME_STAMP
        );

    }


}
