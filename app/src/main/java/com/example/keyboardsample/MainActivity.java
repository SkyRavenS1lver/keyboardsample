package com.example.keyboardsample;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String mSpinnerText;
    Button alertButton;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        mSpinnerText = adapterView.getItemAtPosition
        mSpinnerText = parent.getItemAtPosition(position).toString();
        alertButton = findViewById(R.id.btn_alert);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        showText();
    }

    public void showAlertDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Awas Anjing Galak!");
        alertBuilder.setMessage("Apakah anda tau apa yang ingin saya katakan?");

        alertBuilder.setPositiveButton("Y", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Anda", Toast.LENGTH_SHORT).show();
            }
        });

        alertBuilder.setNegativeButton("N", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Saya", Toast.LENGTH_SHORT).show();
            }
        });
        alertBuilder.setNeutralButton("CUY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Sayton", Toast.LENGTH_SHORT).show();
            }
        });

        alertBuilder.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Making a function to be called by datepicker to show date.
    public void processDatePicker(int day, int month, int year){
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month+1);
        String year_string = Integer.toString(year);
        String dateMessage = day_string + "/" + month_string + "/" + year_string;
        Toast.makeText(this, dateMessage,Toast.LENGTH_SHORT).show();
    }

    public void onProcessTimePicker(int hour, int minute){
        String hours = Integer.toString(hour);
        String minutes = Integer.toString(minute);
        String dateMessage = hours + "." + minutes;
        Toast.makeText(this, dateMessage,Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.label_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        if (spinner != null){
            spinner.setOnItemSelectedListener(this);
        }

        findViewById(R.id.button_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showText();
            }
        });
        findViewById(R.id.btn_datepicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calling (function)
                showDataPicker();
            }
        });
        findViewById(R.id.btn_timepicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calling (function)
                showTimePicker();
            }
        });
    }
    //Showing fragment (function)
    public void showDataPicker(){
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "date-picker");
    }
    public void showTimePicker(){
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "time-picker");
    }


    public void showText() {

        if (mSpinnerText!= null){
            Toast.makeText(this, mSpinnerText, Toast.LENGTH_SHORT).show();
        }


        // Associate editText with the editText_main EditText element.
//        EditText editText = (EditText) findViewById(R.id.editText_main);
//        if (editText != null) {
//            // Assign showString to the text that was entered.
//            String showString = editText.getText().toString();
//            // Make the Toast message with showString.
//            Toast.makeText(this, showString, Toast.LENGTH_SHORT).show();
//        }
    }
}