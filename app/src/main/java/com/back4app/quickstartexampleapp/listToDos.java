package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;




public class listToDos extends AppCompatActivity {
    static ArrayList<String> todos = new ArrayList<String>();
    static ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_dos);

        final ListView listView = findViewById(R.id.listView);

        todos.add("Exmaple");
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, todos);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),EditorActivity.class);
                intent.putExtra("noteId",i);
                startActivity(intent);

            }
        });

        //delete if it is long pressed
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                todos.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });



    }


    //creating menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //menu selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.add_note){
            Intent intent = new Intent(getApplicationContext(),EditorActivity.class);
            startActivity(intent);
            return true;
        }
        else{
            return false;
        }

    }
}