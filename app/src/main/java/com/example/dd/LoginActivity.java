package com.example.dd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText EmailId;
    EditText passwordTx;
    Button LoginBtn;
    Button AskSignUpBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EmailId = findViewById(R.id.emailTxt);
        passwordTx = findViewById(R.id.passwordT);
        LoginBtn = findViewById(R.id.LoginBtn);
        AskSignUpBtn = findViewById(R.id.askSignupBtn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = EmailId.getText().toString();
                String password = passwordTx.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();

                if (Email.isEmpty()){
                    EmailId.setError("Please Enter Your Email Id");
                    EmailId.requestFocus();
                }
                else
                    if (password.isEmpty()){
                        passwordTx.setError("Please Enter Your Password");
                        passwordTx.requestFocus();
                    }
                    else
                        if (!Email.isEmpty() && !password.isEmpty()){
                            firebaseAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, "Login Failed - You Entered wrong details", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
            }
        });


    }
}