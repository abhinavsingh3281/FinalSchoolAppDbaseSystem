package com.example.finalschoolappdbasesystem.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalschoolappdbasesystem.R;
import com.example.finalschoolappdbasesystem.class1.Class1Dashboard;
import com.example.finalschoolappdbasesystem.class1.LoginClass1;
import com.example.finalschoolappdbasesystem.class1.RegisterationClass1;

public class MainActivity extends AppCompatActivity {
    private ImageView classMenuclass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        classMenuclass1=findViewById(R.id.classMenuclass1);


        classMenuclass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ClassMenu.class));
            }
        });


//        TextView textView=findViewById(R.id.tvoverview);
//        TextView signup=findViewById(R.id.tvsignup);
//        TextView adminArea=findViewById(R.id.adminarea);
//
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoginClass1.class));
//            }
//        });
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Class1Dashboard.class));
//            }
//        });
//
//        adminArea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(MainActivity.this, RegisterationClass1.class));
//
//            }
//        });
    }
}
