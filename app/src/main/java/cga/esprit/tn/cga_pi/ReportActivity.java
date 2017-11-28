package cga.esprit.tn.cga_pi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cag.esprit.tn.mail.GMailSender;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        ((Button) findViewById(R.id.sendMail)).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    try {
                        GMailSender sender = new GMailSender("bakker.bmt@gmail.com", "22943307");
                        sender.sendMail("This is Subject",
                                "This is Body",
                                "bakker.bmt@gmail.com",
                                "wael.benboubaker@esprit.tn");
                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }

                }
            });

        }
    }
