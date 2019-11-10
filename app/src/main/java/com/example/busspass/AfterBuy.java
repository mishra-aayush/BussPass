package com.example.busspass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class AfterBuy extends AppCompatActivity {

    public Button mPlus,mMinus,mProceedToPay;
     TextView mQuantity,mTotalPass,mTotalPrice;
    public int quantity,totalpass,totalprice;
    /*private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());*/
    FirebaseUser mUser;
    Button LogOUT ;

    FirebaseAuth mAuth;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_buy);
        Intent intent=getIntent();
        LogOUT = (Button)findViewById(R.id.button1);


        try {
       Objects.requireNonNull(getSupportActionBar()).setTitle("Buy Bus Pass");
    }catch(NullPointerException e){
      e.printStackTrace();
    }
        LogOUT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                finish();
                Toast.makeText(AfterBuy.this, "Log Out Successfull", Toast.LENGTH_LONG).show();
            }


        });


   /* auth = FirebaseAuth.getInstance();
    authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user == null) {
                startActivityForResult(
                        AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .build(),
                        8
                );
            }
        }


    };*/

   //Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);
       mPlus= findViewById(R.id.plus);
        mMinus= findViewById(R.id.minus);
        mProceedToPay= findViewById(R.id.proceed_to_pay);

        mQuantity=findViewById(R.id.quantity);
        mTotalPass=findViewById(R.id.total_pass);
        mTotalPrice=findViewById(R.id.total_price);

       /* GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount acct= GoogleSignIn.getLastSignedInAccount(this);
*/
    }


   public void doMinus(View view)
    {String s,k;
        quantity= Integer.parseInt(mQuantity.getText().toString());
        if(quantity==1)
        {
            Toast.makeText(this,"Minimum Quantity:1",Toast.LENGTH_LONG).show();
        }

        else{
            quantity--;

            s = Integer.toString(quantity);
        mQuantity.setText(s);

        }
        mTotalPass.setText("");
        totalprice=quantity*25;
        k=Integer.toString(totalprice);
        mTotalPrice.setText(k);

    }
    public void doPlus(View view){
        String m,p;
        quantity= Integer.parseInt(mQuantity.getText().toString());
        p=Integer.toString(totalprice);
        m=Integer.toString(quantity);

        if(quantity==10)
        {
            Toast.makeText(this,"Maximum Quantity:10",Toast.LENGTH_LONG).show();
        }
       else{
            quantity++;

            mQuantity.setText(m);
        }

        mTotalPass.setText(m);
        totalprice=quantity*25;
        mTotalPrice.setText(p);
    }

    public void proceedToPay(View view){


    }

    /*@Override
    protected void onPause() {
        super.onPause();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        auth.removeAuthStateListener(authStateListener);
    }*/
}

