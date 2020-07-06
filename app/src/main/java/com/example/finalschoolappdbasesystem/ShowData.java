package com.example.finalschoolappdbasesystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalschoolappdbasesystem.class1.Class1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Module module;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        databaseReference= FirebaseDatabase.getInstance().getReference("Class1");
        listView=findViewById(R.id.listviewtxt);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        module=((Module)getApplicationContext());
        btnUpdate=findViewById(R.id.btnUpdate);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value=snapshot.getValue(Class1.class).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //UpdateInput(arrList.get(position),position);
                module.setGvalue_id(arrayList.get(position));
                module.setGvalue_Name(arrayList.get(position));
                //Toast.makeText(Showdata.this,arrList.get(position),Toast.LENGTH_LONG).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(module.getGvalue_id().equals("")){
                    Toast.makeText(ShowData.this,"Please Select Items",Toast.LENGTH_LONG).show();
                }else {
                    Intent intphto =new Intent(getApplicationContext(),Update.class);
                    startActivity(intphto);
                }
            }
        });
    }
}