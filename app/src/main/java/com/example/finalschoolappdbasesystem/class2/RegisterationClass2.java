package com.example.finalschoolappdbasesystem.class2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalschoolappdbasesystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterationClass2 extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextPassword, editTextPhone;


    private EditText marksEnglish,marksHindi,marksMaths,marksEVS,marksSocialScience;

    private ProgressBar progressBar;
    private Button button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_class2);

        editTextName = findViewById(R.id.nameclass2);
        editTextEmail = findViewById(R.id.emailclass2);
        editTextPassword = findViewById(R.id.passwordclass2);
        editTextPhone = findViewById(R.id.phoneclass2);
        progressBar = findViewById(R.id.progressbarclass2);
        progressBar.setVisibility(View.GONE);

        marksEnglish=findViewById(R.id.marksEnglishclass2);
        marksMaths=findViewById(R.id.marksMathsclass2);
        marksEVS=findViewById(R.id.marksEvsclass2);
        marksHindi=findViewById(R.id.marksHindiclass2);
//        marksSocialScience=findViewById(R.id.marksSocialScience);


        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.button_register_class2).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();


        final String english = marksEnglish.getText().toString().trim();
        final String hindi = marksHindi.getText().toString().trim();
        final String maths = marksMaths.getText().toString().trim();
        final String evs = marksEVS.getText().toString().trim();
//        final String socialScience = marksSocialScience.getText().toString().trim();


        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Class2 class2 = new Class2(
                                    name,
                                    email,
                                    phone,english,hindi,maths,evs
                            );

                            FirebaseDatabase.getInstance().getReference("Class2")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(class2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterationClass2.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegisterationClass2.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register_class2:
                registerUser();
                break;
        }
    }
}