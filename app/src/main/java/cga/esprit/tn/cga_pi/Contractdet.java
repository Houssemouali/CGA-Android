package com.example.asus.cga;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class Contractdet extends AppCompatActivity {
    String text="";
    TextView m_response;

    PayPalConfiguration m_configuration;
    Intent m_service;
    int m_paypalRequestCode= 999;
    String m_paypalClientId = "AQzJIc7tF7MG1pQs4n95oE_hgk3IQbzHtQbuplu8CWWFKYS84b9vjlqKjfR7uGAdFud_7Gi7zYLbaPTe";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_response = (TextView) findViewById(R.id.response);
        setContentView(R.layout.activity_contractdet);
        Bundle bundle= getIntent().getExtras();

        String id= bundle.getString("id");
       String etat= bundle.getString("etat");
        String dateSending = bundle.getString("dateSending");
   //     String wording = bundle.getString("wording");
        String startDate = bundle.getString("startDate");
        String endDate = bundle.getString("endDate");
        String costContract = bundle.getString("costContract");


        TextView detail= (TextView) findViewById(R.id.detail);
        detail.setText("Num Contract : "+id+"\n etat : "+etat+"\n Sending Date : "+dateSending+"\n Start Date:  : "+startDate+"\n End Date:  : "+endDate+"\n cost Contract:  : "+costContract);
m_configuration = new PayPalConfiguration()
        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(m_paypalClientId);
        m_service = new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
        startService(m_service);
    }
    void pay(View view){


        PayPalPayment payment = new PayPalPayment(new BigDecimal(10), "USD", "Test payement with paypal",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent, m_paypalRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == m_paypalRequestCode)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null)
                {
                    String state = confirmation.getProofOfPayment().getState();
                    if (state.equals("approved"))
                        m_response.setText("approved");

                        else
                            m_response.setText("error");
                    }

                else m_response.setText("is null");
            }
        }
    }
}
