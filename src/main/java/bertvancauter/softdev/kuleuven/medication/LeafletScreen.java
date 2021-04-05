package bertvancauter.softdev.kuleuven.medication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LeafletScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaflet_screen);

        final EditText nameOfMedicationEditText = (EditText) findViewById(R.id.namOfMedicationEditText);
        final Button searchLeafletBtn = (Button) findViewById(R.id.searchLeafletBtn);
        searchLeafletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameOfMedicationEditText.getText().equals(""))
                {
                    Toast.makeText(LeafletScreen.this, "fill in the searchbar", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // TODO: 29/05/2019 Hier de database koppelen aan de zoek opdracht!
                    String naam = nameOfMedicationEditText.getText().toString();
                   /* if (naam in database)
                    {
                       // ga naar bijsluiter;
                    }
                    else
                    {
                        Toast.makeText(LeafletScreen.this, "Medication could not be found in our database", Toast.LENGTH_SHORT).show();
                    }*/


                }
            }
        });
    }
}
