package sg.edu.rp.c346.id18004536.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    Button split;
    Button reset;
    TextView bill;
    TextView pays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.etAmount);
        pax = findViewById(R.id.etPax);
        svs = findViewById(R.id.tglSvs);
        gst = findViewById(R.id.tglGst);
        discount = findViewById(R.id.etDiscount);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        bill = findViewById(R.id.bill);
        pays = findViewById(R.id.pays);


        split.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String amount1 = amount.getText().toString();
                String pax1 = pax.getText().toString();
                String discount1 = discount.getText().toString();

                double gstCalc = (Double.parseDouble(amount1) * 7) / 100;
                double svsCalc = (Double.parseDouble(amount1) * 10) / 100;
                double spl = 0;
                double totalbill = 0;


                if (!svs.isChecked() && !gst.isChecked()) {
                    spl = Double.parseDouble(amount1) / Double.parseDouble(pax1);
                    totalbill = Double.parseDouble(amount1);
                } else if (svs.isChecked() && !gst.isChecked()) {
                    totalbill = Double.parseDouble(amount1) + svsCalc;
                    spl = totalbill / Double.parseDouble(pax1);
                } else if (!svs.isChecked() && gst.isChecked()) {
                    totalbill = Double.parseDouble(amount1) + gstCalc;
                    spl = totalbill / Double.parseDouble(pax1);
                } else {
                    totalbill = Double.parseDouble(amount1) + svsCalc + gstCalc;
                    spl = totalbill / Double.parseDouble(pax1);
                }


                if (discount1.trim().length() != 0) {
                    double discCalc = 1 - Double.parseDouble(amount1) / 100;
                    totalbill *= 1 - Double.parseDouble(discount1) / 100;
                }


                String billformat = String.format("Total Bill: $%.2f", totalbill);
                String paysFormat = String.format("Each Pays: $%.2f", spl);
                bill.setText(billformat);
                pays.setText(paysFormat);
            }
        });



        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                amount.setText("");
                pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
                bill.setText("Total Bill:");
                pays.setText("Each Pays:");
            }

        });
    }



    }
