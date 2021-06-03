package com.example.dd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText EmailID;
    EditText PasswordT;
    Button signUpBtn;
    Button askLoginBtn;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmailID = findViewById(R.id.emailText);
        PasswordT = findViewById(R.id.passwordText);
        signUpBtn = findViewById(R.id.SignupBtn);
        askLoginBtn = findViewById(R.id.askLoginBtn);

        askLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = EmailID.getText().toString();
                String Password = PasswordT.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();

                if (Email.isEmpty()){
                    EmailID.setError("Please enter Your Email ID");
                    EmailID.requestFocus();
                }
                else 
                    if (Password.isEmpty()){
                        PasswordT.setError("Please enter Your Password");
                        PasswordT.requestFocus();
                    }
                    else 
                        if (!Email.isEmpty() && !Password.isEmpty()){
                            firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "Signup successfull", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this,HomeActivity.class));

                                    }
                                    
                                }
                            });
                            
                        }

            }
        });



    }


}
