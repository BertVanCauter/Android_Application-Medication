package bertvancauter.softdev.kuleuven.medication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AcountScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button lgnBtn = (Button) findViewById(R.id.loginBtn2);
        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 29/05/2019 Hier moeten de ingegeven gegevens worden vergeleken met gegeven uit de database dan moet er twee scenario's zijn
                /*if (gelukt)
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AcountScreen.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }*/
            }
        });


    }

}
