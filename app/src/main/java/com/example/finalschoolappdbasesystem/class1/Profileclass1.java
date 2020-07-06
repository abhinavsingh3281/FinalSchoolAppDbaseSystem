package com.example.finalschoolappdbasesystem.class1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finalschoolappdbasesystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class Profileclass1 extends AppCompatActivity {


    private TextView name, phone, email;
    private ImageView profileback;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileclass1);

       name = findViewById(R.id.tvprofilename);
       phone = findViewById(R.id.tvProfileNumber);
      email = findViewById(R.id.tvProfileEmail);
        //abhiabhinav13281@gmail.com

        profileback=findViewById(R.id.profileback);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        FirebaseDatabase.getInstance().getReference("Class1")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Class1 class1 = dataSnapshot.getValue(Class1.class);
                name.setText(class1.getNameclass1()+"");
                phone.setText( class1.getPhoneclass1() + "");
                email.setText( class1.getEmailclass1()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profileclass1.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        profileback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profileclass1.this,Class1Dashboard.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
