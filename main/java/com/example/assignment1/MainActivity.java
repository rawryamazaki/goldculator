package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    EditText weight;
    EditText currentvalue;
    RadioButton radioButton;
    RadioGroup radioGroup;
    Button calculate, clear;
    TextView output, output2, output3;
    double type_weight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = (EditText) findViewById(R.id.weight);
        currentvalue = (EditText) findViewById(R.id.currentvalue);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        calculate = (Button) findViewById(R.id.calculate);
        clear = (Button) findViewById(R.id.clear);
        output =(TextView) findViewById(R.id.output);
        output2 =(TextView) findViewById(R.id.output2);
        output3 =(TextView) findViewById(R.id.output3);

        calculate.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.about:
                //Toast.makeText(this, "This is about", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);

                break;

            case R.id.calculator:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);

                break;
        }

        return true;
    }

    public void checkButton(View view) {

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);

            if (radioButton.getText().equals("Keep (85g)")) {
                type_weight = 85;
            } else if (radioButton.getText().equals("Wear (200g)")) {
                type_weight = 200;
            }
        }


        @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.calculate:

                try {

                double final_weight = Double.parseDouble(weight.getText().toString());
                double final_value = Double.parseDouble(currentvalue.getText().toString());

                double final_times = final_weight * final_value;
                double uruf = final_weight - type_weight;
                double payable = uruf * final_value;
                if(payable <= 0)
                    payable = 0;
                double total = payable * 0.025;

                if(radioButton.getText().equals(null)) {
                    Toast.makeText(MainActivity.this,"Please choose type of gold", Toast.LENGTH_SHORT).show();
                }

                df.setRoundingMode(RoundingMode.HALF_UP);
                output.setText("Total value of the gold : RM " + df.format(final_times));
                output2.setText("Zakat payable : RM " + df.format(payable));
                output3.setText("Total zakat : RM " + df.format(total));

                }

                catch (java.lang.Throwable ex) {
                    Toast.makeText(this,"Please enter a value of choose type of gold", Toast.LENGTH_SHORT).show();
                }

            break;

            case R.id.clear:

                clear.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick (View v){
                        weight.setText("");
                        radioGroup.clearCheck();
                        currentvalue.setText("");
                        output.setText("");
                        output2.setText("");
                        output3.setText("");

                        Toast.makeText(MainActivity.this, "Item cleared", Toast.LENGTH_LONG).show();
                    }
                });

            break;
        }
    }
}