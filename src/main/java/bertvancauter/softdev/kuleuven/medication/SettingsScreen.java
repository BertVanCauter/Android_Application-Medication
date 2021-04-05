package bertvancauter.softdev.kuleuven.medication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingsScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                showToast();
                logout();
                //andere logout functies
                startActivity(intent);

            }
        });

        Button deleteAllMedBtn = findViewById(R.id.deleteAllMedBtn);
        deleteAllMedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void showToast()
    {
        Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
    }
    public void logout()
    {
        // Wat moet er gebeuren wanneer men uitlogt
    }
}
