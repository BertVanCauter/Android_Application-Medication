package bertvancauter.softdev.kuleuven.medication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class LoginScreen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText email;
    private String emailString;
    private EditText password;
    private String passwordString;
    private EditText name;
    private String nameString;
    private EditText lastName;
    private String lastNameString;
    private EditText phoneNumber;
    private String phoneNumberString;
    private Button birthdayBtn;
    private TextView failedLoginMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        email = (EditText) findViewById(R.id.emailEditText);
        emailString = email.getText().toString();
        password = (EditText) findViewById(R.id.passwordEditText);
        passwordString = password.getText().toString();
        name = (EditText)findViewById(R.id.naamEditText);
        nameString = name.getText().toString();
        lastName =(EditText)findViewById(R.id.lastNameEditText);
        lastNameString = lastName.getText().toString();
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        phoneNumberString = phoneNumber.getText().toString();
        failedLoginMessage = (TextView) findViewById(R.id.failedLoginTextView);


        //Hier maak ik de tekst clickable
        TextView acountTextView = (TextView) findViewById(R.id.acountLinkTextView);
        String acountLink = "I already have an acount";
        SpannableString ss = new SpannableString(acountLink);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick( View widget)
            {
                     Intent openAcountScreen = new Intent(getApplicationContext(),AcountScreen.class);
                     startActivity(openAcountScreen);
            }
        };
        ss.setSpan(clickableSpan1, 0, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        acountTextView.setText(ss);
        acountTextView.setMovementMethod(LinkMovementMethod.getInstance());


        birthdayBtn = (Button) findViewById(R.id.birthdayBtn);
        birthdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"DatePicker");
            }
        });

        //Hier zorgt ik ervoor dat alle velden ingevuld moeten zijn en dat men dan pas op registreren kan drukken.
        // Verder zorgt ik er ook voor dat de inhoud van de velden wordt meegegeven naar het mainscherm
        final Button registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().equals("")||password.getText().equals("")||name.getText().equals("")||lastName.getText().equals("")||phoneNumber.getText().equals("")||birthdayBtn.getText().equals("Date"))
                {
                    failedLoginMessage.setText("Fill in all fields");
                }
                else
                {
                    // TODO: 29/05/2019 hier moeten de parameter met de gegevens in de database worden geplaatst! voornamelijk het email in het passwoord!!!
                    // ik heb reeds de nodige parameters aangemaakt.
                }
            }
        });
    }

    //initialisatie van de Datepicker
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        Button birthdayBtn = (Button) findViewById(R.id.birthdayBtn);
        birthdayBtn.setText(currentDateString);

    }
}
