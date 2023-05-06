package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView linkRegister;
    EditText emailField, passwordField;
    Button loginBtn;
//    SharedPreferences sharedPref;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sharedPref = getSharedPreferences("account_user", MODE_PRIVATE);

        linkRegister = findViewById(R.id.tv_registerLink);
        emailField = findViewById(R.id.et_emailLogin);
        passwordField = findViewById(R.id.et_passwordLogin);
        loginBtn = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        linkRegister.setOnClickListener(v->{
            Intent registerIntent = new Intent(MainActivity.this, registerclass.class);
            startActivity(registerIntent);

            startActivity(new Intent(MainActivity.this, registerclass.class));
        });

        loginBtn.setOnClickListener(v->{

            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, task ->{
                if(!task.isSuccessful()){
                    Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
                else{
                    ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("Please Wait....");
                    progressDialog.setMessage("Logging in");
                    progressDialog.setCancelable(true);
                    progressDialog.show();

                    new Handler().postDelayed( () -> {
                        progressDialog.dismiss();
                        Intent homeIntent = new Intent(MainActivity.this, HomePage.class);
                        startActivity(homeIntent);
                        finish();
                    },2000);
                }
            });

        });


    }
}