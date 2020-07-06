package com.example.finalschoolappdbasesystem.class1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalschoolappdbasesystem.home.MainActivity;
import com.example.finalschoolappdbasesystem.R;
import com.example.finalschoolappdbasesystem.TeacherActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginClass1 extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private ImageView back;
    private ImageView Login;

    private CheckBox checkBox;



    private EditText Idd;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_class1);





        Email = (EditText) findViewById(R.id.class1email);
        Password = (EditText) findViewById(R.id.class1password);

        Login = (ImageView) findViewById(R.id.class1login);

        back=findViewById(R.id.backclass1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginClass1.this, MainActivity.class));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate(Email.getText().toString(), Password.getText().toString());

            }
        });



    }

    private void validate(final String userName, String userPassword) {

        if (userName == "teacher@ssbv.com" && userPassword == "123456") {
            startActivity(new Intent(LoginClass1.this, TeacherActivity.class));
        } else {

            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        checkEmailVerification();
                    } else {
                        Toast.makeText(LoginClass1.this, "Login Failed", Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }
    }



    private void checkEmailVerification() {



        startActivity(new Intent(LoginClass1.this, Class1Dashboard.class));




    }
}



