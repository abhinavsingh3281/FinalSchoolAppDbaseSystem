package com.example.finalschoolappdbasesystem.class1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalschoolappdbasesystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Class1Dashboard extends AppCompatActivity {

    ImageView btnHomeclass,btnProfileclass,btnMarksclass,btnLogOutclass;
    TextView nameondashboardclass1;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class1_dashboard);

        btnHomeclass=findViewById(R.id.homepagedashboardclass1);
        btnProfileclass=findViewById(R.id.profiledashboardclass1);
        btnMarksclass=findViewById(R.id.marksdashboardclass1);
        nameondashboardclass1=findViewById(R.id.nameondashboardclass1);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        FirebaseDatabase.getInstance().getReference("Class1")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Class1 class1 = dataSnapshot.getValue(Class1.class);
                nameondashboardclass1.setText(class1.getNameclass1()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Class1Dashboard.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });




        btnHomeclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Class1Dashboard.this, HomeActivityClass1.class));
            }
        });

        btnProfileclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Class1Dashboard.this, Profileclass1.class));
            }
        });

        btnMarksclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Class1Dashboard.this,MarksClass1.class));
            }
        });


    }
}