package com.example.busspass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AfterBuy extends AppCompatActivity {

    private Button mPlus,mMinus,mProceedToPay;
    private TextView mQuantity,mTotalPass,mTotalPrice;
    private int quantity,totalpass,totalprice;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_buy);


        getSupportActionBar().setTitle("Buy Bus Pass");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mPlus=(Button) findViewById(R.id.plus);
        mMinus=(Button) findViewById(R.id.minus);
        mProceedToPay=(Button) findViewById(R.id.proceed_to_pay);

        mQuantity=(TextView)findViewById(R.id.quantity);
        mTotalPass=(TextView)findViewById(R.id.total_pass);
        mTotalPrice=(TextView)findViewById(R.id.total_price);



    }


    public void doMinus(View view)
    {
        quantity= Integer.parseInt(mQuantity.getText().toString());
        if(quantity==1)
        {
            Toast.makeText(this,"Minimum Quantity:1",Toast.LENGTH_LONG).show();
        }

        else{
            quantity--;
            mQuantity.setText(""+quantity);
        }
        mTotalPass.setText(""+quantity);
        totalprice=quantity*25;
        mTotalPrice.setText(""+totalprice);

    }
    public void doPlus(View view){
        quantity= Integer.parseInt(mQuantity.getText().toString());
        if(quantity==10)
        {
            Toast.makeText(this,"Maximum Quantity:10",Toast.LENGTH_LONG).show();
        }
        else{
            quantity++;
            mQuantity.setText(""+quantity);
        }
        mTotalPass.setText(""+quantity);
        totalprice=quantity*25;
        mTotalPrice.setText(""+totalprice);
    }

    public void proceedToPay(View view){


    }



}

