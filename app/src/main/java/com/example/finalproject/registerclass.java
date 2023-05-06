package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerclass extends AppCompatActivity {

    TextView linkLogin;
    EditText idBimbel, usernameField, passwordField, confPasswordField, emailField;
    Button registerBtn;
//    SharedPreferences sharedPref;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerclass);

        linkLogin = findViewById(R.id.tv_loginLink);
        idBimbel = findViewById(R.id.et_idBimbel);
        usernameField = findViewById(R.id.et_usernameRegister);
        passwordField = findViewById(R.id.et_passwordRegister);
        confPasswordField = findViewById(R.id.et_confPasswordRegister);
        emailField = findViewById(R.id.et_emailRegister);
        registerBtn = findViewById(R.id.btn_register);

//        sharedPref = getSharedPreferences("account_user", MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://finalproject-d53e7-default-rtdb.asia-southeast1.firebasedatabase.app/");

        linkLogin.setOnClickListener(v->{
            Intent loginIntent = new Intent(registerclass.this, MainActivity.class);
            startActivity(loginIntent);
        });

        registerBtn.setOnClickListener(v->{

            String bimbel = idBimbel.getText().toString();
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();
            String confPassword = confPasswordField.getText().toString();
            String email = emailField.getText().toString();

            if (bimbel.isEmpty() || username.isEmpty() || password.isEmpty() || confPassword.isEmpty() || email.isEmpty()){
                Toast.makeText(this, "All Field must be filled", Toast.LENGTH_SHORT).show();
            }else if(usernameField.getText().toString().length() < 5){
                Toast.makeText(this, "Username must have more than 5 characters", Toast.LENGTH_SHORT).show();
            }else if (!emailField.getText().toString().contains("@") || !emailField.getText().toString().endsWith(".com")){
                Toast.makeText(this, "Email must contain '@' and ends with '.com' ", Toast.LENGTH_SHORT).show();
            }else if (passwordField.getText().toString().length() < 5){
                Toast.makeText(this, "Password must have more than 5 characters", Toast.LENGTH_SHORT).show();
            }else if (!confPasswordField.getText().toString().equals(passwordField.getText().toString())){
                Toast.makeText(this, "Password and Confirm password must match!", Toast.LENGTH_SHORT).show();
            }else{
//                SharedPreferences.Editor editor = sharedPref.edit();
//                  editor.putString("bimbel_id", usernameField.getText().toString());
//                  editor.putString("username_user",usernameField.getText().toString());
//                  editor.putString("email_user",emailField.getText().toString());
//                  editor.putString("password_user",passwordField.getText().toString());
//                  editor.apply();
//                  Toast.makeText(this, "Register Success!", Toast.LENGTH_SHORT).show();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(registerclass.this, task ->{
                    if(!task.isSuccessful()){
                        Toast.makeText(this, "Email already Exist", Toast.LENGTH_SHORT).show();
                    }else{
                        userReference = firebaseDatabase.getReference().child("users").child(mAuth.getCurrentUser().getUid());
                        userReference.setValue(new User(email, username, password));
                        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}