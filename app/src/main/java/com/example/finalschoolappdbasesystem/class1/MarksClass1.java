package com.example.finalschoolappdbasesystem.class1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalschoolappdbasesystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MarksClass1 extends AppCompatActivity {

    private TextView tvEVSMarks,tvHindiMarks,tvMathMarks,tvEnglishMarks,marksSection;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ImageView profileback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_class1);

        tvEnglishMarks=findViewById(R.id.tvEnglishMarksClass1);
        tvHindiMarks=findViewById(R.id.tvHindiMarksClass1);
        tvEVSMarks=findViewById(R.id.tvEVSMarksClass1);
        tvMathMarks=findViewById(R.id.tvMathMarksClass1);

        marksSection=findViewById(R.id.marksSection);

        profileback=findViewById(R.id.marksback);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        FirebaseDatabase.getInstance().getReference("Class1")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Class1 class1 = dataSnapshot.getValue(Class1.class);
                marksSection.setText(class1.getMarksSectionClass1()+"");
                tvEnglishMarks.setText(class1.getMarksEnglishclass1()+"");
                tvHindiMarks.setText( class1.getMarksHindi() + "");
                tvEVSMarks.setText( class1.getMarksEVSclass1()+"");
                tvMathMarks.setText( class1.getMarksMathsclass1()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MarksClass1.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        profileback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarksClass1.this,Class1Dashboard.class));
            }
        });

        profileback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarksClass1.this,Class1Dashboard.class));
            }
        });

    }
}