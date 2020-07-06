package com.example.finalschoolappdbasesystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalschoolappdbasesystem.Module;
import com.example.finalschoolappdbasesystem.R;
import com.example.finalschoolappdbasesystem.class1.Class1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class Update extends AppCompatActivity {
    EditText updateemailclass1,updatenameclass1,updatemobileclass1,updatemarksEnglishclass1,updatemarksHindiclass1,updatemarksMathsclass1,updatemarksEVSclass1;

    EditText updatemarksheadingclass1,updateattendenceheadingclass1,updateattendenceclass1;
    Button btnUpdate,btnCancel;
    DatabaseReference databaseReference;
    private TextView result;
    Module module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        result=(TextView)findViewById(R.id.textView2);


        updateemailclass1=(EditText) findViewById(R.id.updateemailclass1);
        updatenameclass1=(EditText) findViewById(R.id.updatenameclass1);
        updatemobileclass1=findViewById(R.id.updatemobileclass1);
        updatemarksEnglishclass1=findViewById(R.id.updatemarksEnglishclass1);
        updatemarksHindiclass1=findViewById(R.id.updatemarksHindiclass1);
        updatemarksMathsclass1=findViewById(R.id.updatemarksMathsclass1);
        updatemarksEVSclass1=findViewById(R.id.updatemarksEVSclass1);


        updatemarksheadingclass1=findViewById(R.id.updatemarksheadingclass1);
        updateattendenceheadingclass1=findViewById(R.id.updateattendenceheadingclass1);
        updateattendenceclass1=findViewById(R.id.updateattendenceclass1);



        btnUpdate=(Button) findViewById(R.id.btnUpadate);
        btnCancel= (Button) findViewById(R.id.btnCancel) ;
        databaseReference= FirebaseDatabase.getInstance().getReference("Class1");
        module=((Module)getApplicationContext());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Class1 class1 = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(Class1.class);
                updateemailclass1.setText(class1.getEmailclass1()+"");
                updatenameclass1.setText(class1.getNameclass1()+"");
                updatemobileclass1.setText(class1.getPhoneclass1()+"");
                updatemarksEnglishclass1.setText(class1.getMarksEnglishclass1()+"");
                updatemarksHindiclass1.setText(class1.getMarksHindi()+"");
                updatemarksMathsclass1.setText(class1.getMarksMathsclass1()+"");
                updatemarksEVSclass1.setText(class1.getMarksEVSclass1()+"");
                updatemarksheadingclass1.setText(class1.getMarksSectionClass1()+"");

              //  updateattendenceheadingclass1.setText(class1.getAttendenceHeadingClass1()+"");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateArrayList();
                Cleartxt();
                Intent intphto =new Intent(getApplicationContext(),ShowData.class);
                startActivity(intphto);


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cleartxt();
            }
        });
    }
    private void Cleartxt(){
        updatenameclass1.setText("");
        updateemailclass1.setText("");
        updatemobileclass1.setText("");
        updatemarksEnglishclass1.setText("");
        updatemarksHindiclass1.setText("");
        updatemarksMathsclass1.setText("");
        updatemarksEVSclass1.setText("");

    }

    private void  updateArrayList() {
        final String nameclass1 = updatenameclass1.getText().toString().trim();
        final String emailclass1 = updateemailclass1.getText().toString().trim();
        final String phoneclass1 = updatemobileclass1.getText().toString().trim();
        final String marksEnglishclass1 = updatemarksEnglishclass1.getText().toString().trim();
        final String marksHindi = updatemarksHindiclass1.getText().toString().trim();
        final String marksMathsclass1 = updatemarksMathsclass1.getText().toString().trim();
        final String marksEVSclass1 = updatemarksEVSclass1.getText().toString().trim();

        final String marksSectionClass1=updatemarksheadingclass1.getText().toString().trim();


       // final String attendenceHeading=updateattendenceheadingclass1.getText().toString().trim();
        final String attendence=updateattendenceclass1.getText().toString().trim();


        if (TextUtils.isEmpty(nameclass1)) {
            updatenameclass1.setError("Please enter your name!");
        } else if (TextUtils.isEmpty(emailclass1)) {
            updateemailclass1.setError("Please enter your e-mail!");
        } else if (TextUtils.isEmpty(phoneclass1)) {
            updatemobileclass1.setError("Please enter your mobile no!");
        } else if (TextUtils.isEmpty(marksEnglishclass1)) {
            updatemarksEnglishclass1.setError("Please enter your english marks!");
        } else if (TextUtils.isEmpty(marksHindi)) {
            updatemarksHindiclass1.setError("Please enter your hindi marks!");

        }
        else if (TextUtils.isEmpty(marksMathsclass1)) {
            updatemarksMathsclass1.setError("Please enter your maths marks!");
        }
        else if (TextUtils.isEmpty(marksEVSclass1)) {
            updatemarksEVSclass1.setError("Please enter your evs marks!");
        }
        else {

            Class1 class1 = new Class1(nameclass1,emailclass1, phoneclass1,  marksEnglishclass1,  marksHindi,  marksMathsclass1,  marksEVSclass1,marksSectionClass1);
            databaseReference.child("Class1").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Class1").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("emailclass1").setValue(emailclass1);
                    databaseReference.child("Class1").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("nameclass1").setValue(nameclass1);

                    //add more fields here to change

                    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });

            Toast.makeText(this, "Student is updated", Toast.LENGTH_LONG).show();
            Cleartxt();

        }
    }
}
