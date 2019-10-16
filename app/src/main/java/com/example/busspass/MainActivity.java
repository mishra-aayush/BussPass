package com.example.busspass;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    static final int GOOGLE_SIGN_IN = 123;
    FirebaseAuth mAuth;
    Button btn_login, btn_logout,signin,signup;
    TextView text;
    ProgressBar progressBar;
    GoogleSignInClient mGoogleSignInClient;
    TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.loginG);
        btn_logout = findViewById(R.id.logout);
        signin=findViewById(R.id.sign_in);
        signup=findViewById(R.id.sign_up);
        text = findViewById(R.id.text);
        forgotPassword=findViewById(R.id.forgot_password);
        progressBar = findViewById(R.id.progress_circular);
        mAuth = FirebaseAuth.getInstance();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btn_login.setOnClickListener(v->SignInGoogle());
        btn_logout.setOnClickListener(v->Logout());
        if(mAuth.getCurrentUser()!=null){
            FirebaseUser user= mAuth.getCurrentUser();
            updateUI(user);
        }

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ForgotPassword.class));
            }
        });

    }
    void SignInGoogle(){
        progressBar.setVisibility(View.VISIBLE);
        Intent signIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent,GOOGLE_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GOOGLE_SIGN_IN){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account= task.getResult(ApiException.class);
                if(account!=null)
                    firebaseAuthWithGoogle(account);

            }catch (ApiException e){
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account){
        Log.d("TAG","fireBaseAuthWithGoogle"+ account.getId());
        AuthCredential credential=GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task-> {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.d("Tag", "Signin success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.w("Tag","sign in failure",task.getException());
                            Toast.makeText(this, "SignInFailed", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }


                });
    }

    /* btn_login.setOnClickListener(v -> SignInGoogle());
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
        }*/

private void updateUI(FirebaseUser user){
    if(user!=null ){
        String name=user.getDisplayName();
        String email= user.getEmail();
        text.append("Info:\n");
        text.append(name+"\n");
        text.append(email);
        progressBar.setVisibility(View.INVISIBLE);
        btn_login.setVisibility(View.INVISIBLE);
        btn_logout.setVisibility(View.VISIBLE);
    }else{
        text.setText(getString(R.string.login));
        btn_login.setVisibility(View.INVISIBLE);

        }


    }
void Logout(){
    FirebaseAuth.getInstance().signOut();
    mGoogleSignInClient.signOut()
            .addOnCompleteListener(this,task ->updateUI(null));
}

}
