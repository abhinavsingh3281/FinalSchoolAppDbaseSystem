package com.example.finalschoolappdbasesystem.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finalschoolappdbasesystem.R;
import com.example.finalschoolappdbasesystem.class1.LoginClass1;

public class ClassMenu extends AppCompatActivity {

    private ImageView classonehome,classtwohome,classthreehome,classfourhome,class5home,classsixhome,classsevenhome,classeighthome,classninehome,classtenhome,classelevenhome,classtwelvehome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_menu);


        classonehome=findViewById(R.id.classonehome);
        classtwohome=findViewById(R.id.classtwohome);
        classthreehome=findViewById(R.id.classthreehome);
        classfourhome=findViewById(R.id.classfourhome);
        class5home=findViewById(R.id.classfivehome);
        classsixhome=findViewById(R.id.classsixhome);
        classsevenhome=findViewById(R.id.classsevenhome);
        classeighthome=findViewById(R.id.classeighthome);
        classninehome=findViewById(R.id.classninehome);
        classtenhome=findViewById(R.id.classtenhome);
        classelevenhome=findViewById(R.id.classelevenhome);
        classtwelvehome=findViewById(R.id.classtwelvehome);

        classonehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClassMenu.this, LoginClass1.class));
            }
        });
    }
}