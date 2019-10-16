package com.example.busspass;

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

public class SignUp extends AppCompatActivity {
        private EditText userName,userPassword,userEmail;
        private Button regbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName=findViewById(R.id.e_email);
        userPassword=findViewById(R.id.et_pass);
        userEmail=findViewById(R.id.et_name);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

        regbtn=findViewById(R.id.register_btn);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String user_Email=userEmail.getText().toString().trim();
                    String user_password= userPassword.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_Email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignUp.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }

        });



    }
}
