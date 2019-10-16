package com.example.busspass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private EditText cEmail;
    private Button resetPassword;
    private FirebaseAuth fireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        cEmail=findViewById(R.id.emailAdd);
        resetPassword=findViewById(R.id.resetPassword);
        fireBaseAuth=FirebaseAuth.getInstance();
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail= cEmail.getText().toString().trim();
                if(useremail.equals("")){
                    Toast.makeText(ForgotPassword.this,"Please Enter your registered Email",Toast.LENGTH_LONG).show();

                }
                else{
                    fireBaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ForgotPassword.this,"Password Reset Successful",Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(ForgotPassword.this,MainActivity.class));
                                }else{
                                    Toast.makeText(ForgotPassword.this,"Password Reset UnSuccessful",Toast.LENGTH_LONG).show();
                                }
                        }
                    });
                }

            }
        });
    }

}
