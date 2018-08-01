package sg.edu.rp.c346.reservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvPhone, tvPax;
    EditText etName, etPhone, etPax, etDay, etTime;
    Button btnReserve, btnReset;
    CheckBox cbSmoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.textViewName);
        etName = findViewById(R.id.editTextName);
        tvPhone = findViewById(R.id.textViewPhone);
        etPhone = findViewById(R.id.editTextPhone);
        tvPax = findViewById(R.id.textViewPax);
        etPax = findViewById(R.id.editTextPax);
        cbSmoke = findViewById(R.id.checkBoxSmoke);
        etDay = findViewById(R.id.editTextDate);
        etTime = findViewById(R.id.editTextTime);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);

        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener  = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etDay.setText("Date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                };

                Calendar cal = Calendar.getInstance();
                int nYear = cal.get(Calendar.YEAR);
                int nMonth = cal.get(Calendar.MONTH);
                int nDay = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, nYear, nMonth, nDay);
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etTime.setText("Time: "+hourOfDay+":"+minute);
                    }
                };

                Calendar cal = Calendar.getInstance();
                int nHour = cal.get(Calendar.HOUR_OF_DAY);
                int nMinute = cal.get(Calendar.MINUTE);

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, nHour, nMinute, true);
                myTimeDialog.show();
            }
        });

        btnReserve.setOnClickListener(
                            (new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View viewDialog =inflater.inflate(R.layout.activity_main, null);
                                String isSmoke = "";
                                if (cbSmoke.isChecked()) {
                                    isSmoke = "Smoking";
                                } else {
                                    isSmoke = "Non-Smoking";
                                }
                            AlertDialog.Builder myBuilder=new AlertDialog.Builder(MainActivity.this);
                                myBuilder.setView(viewDialog);
                                myBuilder.setTitle("Confirm Your Order");
                                myBuilder.setMessage("New Reservation \n"+"Name: "+etName.getText().toString()+"\n"+isSmoke+"\nSize: "+etPax.getText().toString()+"\n"+etDay.getText().toString()+"\n"+etTime.getText().toString());

                                myBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                myBuilder.setNeutralButton("CANCEL", null);
                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();
                            }
                        })
        );

                        btnReset.setOnClickListener(
                                (new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        etName.setText(null);
                                        etPhone.setText(null);
                                        etPax.setText(null);
                                        cbSmoke.setChecked(false);
                                        etDay.setText(null);
                                        etTime.setText(null);
                                    }
                                })
                        );
    }
}
