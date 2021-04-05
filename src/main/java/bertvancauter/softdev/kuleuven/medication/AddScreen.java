package bertvancauter.softdev.kuleuven.medication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


public class AddScreen extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private LocalTime tijd;
    private Date datum;
    private Medication medicatie;
    private int year;
    private int month;
    private int day;
    private int uren;
    private int minuten;
    private Calendar c;
    private boolean notificationBoolean;


    private String name;
    private int number;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);


        getSupportActionBar().setTitle("Add Medication");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final Button tijdKnop = (Button) findViewById(R.id.timeBtn);

        final TextView aantalPillen = (TextView) findViewById(R.id.numberOfPillsTextView);

        final Switch emailNotif = (Switch) findViewById(R.id.emailSwitch);
        final Switch pushNotif = (Switch) findViewById(R.id.pushnotificationSwitch);

        final EditText naamMedicatie = (EditText) findViewById(R.id.medicationNameEditText);


        if(getIntent().hasExtra("year")&& getIntent().hasExtra("month")&& getIntent().hasExtra("day"))
        {
            TextView date = (TextView) findViewById(R.id.date);
            year = getIntent().getExtras().getInt("year");
            String jaar = Integer.toString(year);
            month = getIntent().getExtras().getInt("month");
            String maand = Integer.toString(month);
            day = getIntent().getExtras().getInt("day");
            String dag = Integer.toString(day);
            datum = new Date(year, month, day);
            date.setText(datum.getDate()+"/"+datum.getMonth()+"/"+datum.getYear());
        }

        Button increaseBtn = (Button) findViewById(R.id.increaseBtn);
        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView aantalpillen = (TextView) findViewById(R.id.numberOfPillsTextView);
                int getal = Integer.parseInt(aantalpillen.getText().toString());
                if(getal >= 1 && getal <99)
                {
                    getal++;
                }
                aantalpillen.setText(getal+"");
            }
        });

        Button decreaseBtn = (Button) findViewById(R.id.decreaseBtn);
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView aantalpillen = (TextView) findViewById(R.id.numberOfPillsTextView);
                int getal = Integer.parseInt(aantalpillen.getText().toString());
                if (getal >1)
                {
                    getal--;
                }
                aantalpillen.setText(getal+"");
            }
        });

        Switch emailSwitch = (Switch) findViewById(R.id.emailSwitch);
        emailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                // hier nog een mail notificatie verzenden
            }
        });

        Switch notificationSwitch = (Switch) findViewById(R.id.pushnotificationSwitch);
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (!tijdKnop.getText().equals("Time"))
                {
                    int hourOfDay = tijd.getHour();
                    int minute = tijd.getMinute();
                    c = Calendar.getInstance();
                    //c.set(Calendar.YEAR,jaar);
                    //c.set(Calendar.MONTH,maand);
                    //c.set(Calendar.DAY_OF_MONTH,dag);
                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    c.set(Calendar.MINUTE, minute);
                    c.set(Calendar.SECOND, 0);
                    startAlarm(c);
                }
                notificationBoolean = isChecked;

            }
        });


        tijdKnop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"TimePicker");

            }
        });

        Button addBtn = (Button) findViewById(R.id.addBtn2);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (naamMedicatie.getText().equals("")|| tijdKnop.getText().equals("Time"))
                {
                    TextView errorMessage = (TextView) findViewById(R.id.errorMessageTextView);
                    errorMessage.setText("Fill in all fields");
                }
                else
                {
                    Switch notify = (Switch) findViewById(R.id.pushnotificationSwitch);
                    if (notificationBoolean)
                    {
                        //startAlarm(c);
                        //Hier nog de startNotification methode oproepen maar eerst de gegevens van de klok in een variabele steken!
                    }
                    //medicatie = new Medication(naamMedicatie.getText().toString(), Integer.parseInt(aantalPillen.getText().toString()), emailNotif.getShowText(), pushNotif.getShowText(),year, month, day, uren, minuten);
                    Intent saveAndGoBack = new Intent();
                    //saveAndGoBack.putExtra("medicatie", medicatie);
                    //proberen new object aan te maken in andere klasse. en niet hier al aan te maken en mee te geven.
                    saveAndGoBack.putExtra("naam", naamMedicatie.getText().toString());
                    saveAndGoBack.putExtra("aantal",Integer.valueOf(aantalPillen.getText().toString()) );
                    saveAndGoBack.putExtra("email", emailNotif.isChecked());
                    saveAndGoBack.putExtra("push",pushNotif.isChecked());
                    saveAndGoBack.putExtra("jaar", year);
                    saveAndGoBack.putExtra("maand", month);
                    saveAndGoBack.putExtra("dag",day);
                    saveAndGoBack.putExtra("uur",uren);
                    saveAndGoBack.putExtra("minuten",minuten);
                    setResult(RESULT_OK, saveAndGoBack);
                    finish();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        tijd = LocalTime.of(hourOfDay, minute);
        uren = hourOfDay;
        minuten = minute;
        Button tijdBtn = (Button) findViewById(R.id.timeBtn);
        if(minute < 10)
        {
            tijdBtn.setText(hourOfDay+":0"+minute);
        }
        else
        {
            tijdBtn.setText(hourOfDay+":"+minute);
        }
    }

    private void startAlarm(Calendar c)
    {
        EditText naam = (EditText) findViewById(R.id.medicationNameEditText) ;
        TextView aantal = (TextView) findViewById(R.id.numberOfPillsTextView);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent (this, AlertReceiver.class);
        intent.putExtra("naam",naam.getText().toString()+"");
        intent.putExtra("aantal",aantal.getText().toString()+"");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent (this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1, intent, 0);

        alarmManager.cancel(pendingIntent);
    }

}
