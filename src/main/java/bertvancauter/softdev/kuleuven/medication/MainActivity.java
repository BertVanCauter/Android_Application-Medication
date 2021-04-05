package bertvancauter.softdev.kuleuven.medication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private boolean login;
    private CalendarView calendarView;
    private String year;
    private int yearInt;
    private String month;
    private int monthInt;
    private String day;
    private int dayInt;
    private ArrayList<Medication> medicationList;
    //private ArrayList<ExampleMedicatieObject> exampleMedicatieObjectArrayList;

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
    /*public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "medicationListPrefs";

    private String textOfList;*/

    private boolean delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        getSupportActionBar().setTitle("Homescreen");

        Calendar c = Calendar.getInstance();
        yearInt = c.get(Calendar.YEAR);
        year = yearInt + "";
        monthInt = c.get(Calendar.MONTH);
        month = monthInt + "";
        dayInt = c.get(Calendar.DAY_OF_MONTH);
        day = dayInt + "";
        updateList(yearInt,monthInt,dayInt);



        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int jaar, int maand, int dag) {
                year = jaar + "";
                yearInt = jaar;
                month = maand + 1 + "";
                monthInt = maand + 1;
                day = dag + "";
                dayInt = dag;
                //calenderDag = new Date(jaar, maand, dag);
                updateList(yearInt, monthInt, dayInt);
                System.out.println(yearInt + "/" + monthInt + "/" + dayInt);
            }
        });



        Button addButton = findViewById(R.id.addMedicationBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addScreenIntent = new Intent(getApplicationContext(), AddScreen.class);
                addScreenIntent.putExtra("year", yearInt);
                addScreenIntent.putExtra("month", monthInt);
                addScreenIntent.putExtra("day", dayInt);
                startActivityForResult(addScreenIntent, 1);
            }
        });


        Button loginBtn = findViewById(R.id.RegisterBtnMain);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(loginIntent);
            }
        });

        Button leafletBtn = (Button) findViewById(R.id.leafletBtn);
        leafletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leafletIntent = new Intent (getApplicationContext(),LeafletScreen.class);
                startActivity(leafletIntent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //Medication nieuweMedicatie = intent.getParcelableExtra("medicatie");
                // medicationList.add(nieuweMedicatie);
                String naam = data.getStringExtra("naam");
                int aantal = data.getIntExtra("aantal", 1);
                boolean emailCheck = data.getBooleanExtra("email", false);
                boolean pushCheck = data.getBooleanExtra("push", false);
                int jaar = data.getIntExtra("jaar", 2019);
                int maand = data.getIntExtra("maand", 5);
                int dag = data.getIntExtra("dag", 25);
                int uren = data.getIntExtra("uren", 0);
                int minuten = data.getIntExtra("minuten", 0);
                System.out.println(naam);
                System.out.println(aantal);
                Medication medicatie = new Medication(naam, aantal, emailCheck, pushCheck, jaar, maand, dag, uren, minuten);
                medicationList.add(medicatie);
                savaData();
                for (Medication drug: medicationList)
                {
                    System.out.println(drug.getYear()+"");
                    System.out.println(drug.getMonth()+"");
                    System.out.println(drug.getDay()+"");
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }

    private void savaData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(medicationList);
        editor.putString("medication list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("medication list", null);
        Type type = new TypeToken<ArrayList<Medication>>() {
        }.getType();
        medicationList = gson.fromJson(json, type);

        if (medicationList == null) {
            medicationList = new ArrayList<>();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsScreen.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void updateList(int jaren, int maanden, int dagen)
    {
        ArrayList<ExampleMedicatieObject> exampleMedicatieObjectArrayList = new ArrayList<>();
        if (!medicationList.isEmpty())
        {
            try {
                for (Medication drug : medicationList) {
                    if ((jaren == drug.getYear()) && (maanden == drug.getMonth()) && (dagen == drug.getDay())) {
                        //ArrayList<ExampleMedicatieObject> exampleMedicatieObjectArrayList = new ArrayList<>();
                        String name = drug.getName();
                        String aantal = Integer.toString(drug.getNumber());
                        exampleMedicatieObjectArrayList.add(new ExampleMedicatieObject(R.drawable.ic_alarmbell, name, "neem er: " + aantal,drug.getYear(), drug.getMonth(),drug.getDay()));
                        //hier de naam en aantal toevoegen bij de lijsten die we daarna in de listview willen steken
                        updateRecycleView(exampleMedicatieObjectArrayList);
                    }
                    else
                    {
                        ExampleMedicatieObject leegObject = new ExampleMedicatieObject(R.drawable.ic_alarmbell, "Geen medicatie", "Druk op de addBtn",0,0,0);
                        ArrayList<ExampleMedicatieObject> standardList = new ArrayList<>();
                        standardList.add(leegObject);
                        updateRecycleView(standardList);
                    }
                }
                if (!exampleMedicatieObjectArrayList.isEmpty())
                {
                    updateRecycleView(exampleMedicatieObjectArrayList);
                }
                else
                {
                    ExampleMedicatieObject leegObject = new ExampleMedicatieObject(R.drawable.ic_aanduidpijl, "Geen midicatie toegevoegd", "",0,0,0);
                    ArrayList<ExampleMedicatieObject> standardList = new ArrayList<>();
                    standardList.add(leegObject);
                    updateRecycleView(standardList);
                }
            } catch (NullPointerException e) {
                ExampleMedicatieObject leegObject = new ExampleMedicatieObject(R.drawable.ic_alarmbell, "Geen midicatie", "",0,0,0);
                ArrayList<ExampleMedicatieObject> standardList = new ArrayList<>();
                standardList.add(leegObject);
                updateRecycleView(standardList);
            }
        }
        else
        {
            ExampleMedicatieObject leegObject = new ExampleMedicatieObject(R.drawable.ic_aanduidpijl, "Geen midicatie toegevoegd", "",0,0,0);
            ArrayList<ExampleMedicatieObject> standardList = new ArrayList<>();
            standardList.add(leegObject);
            updateRecycleView(standardList);
        }
    }

    public void removeItem(int position, ArrayList<ExampleMedicatieObject> exampleMedicatieObjectArrayList)
    {
        ExampleMedicatieObject object = exampleMedicatieObjectArrayList.get(position);
        try
        {
            for (Medication drug : medicationList)
            {
                int j = drug.getYear();
                int m = drug.getMonth();
                int d = drug.getDay();
                String name = drug.getName();
                if((j == object.getJaar()) && (m == object.getMaand()) && (d == object.getDag()) &&  name.equals(object.getText1()))
                {
                    exampleMedicatieObjectArrayList.remove(object);
                    medicationList.remove(drug);
                }
            }
            savaData();
            updateRecycleView(exampleMedicatieObjectArrayList);
        }
        catch (NullPointerException e)
        {
        }
    }



    public void updateRecycleView (final ArrayList <ExampleMedicatieObject> exampleMedicatieObjectArrayList)
    {
        mRecyclerView = findViewById(R.id.medicationRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(exampleMedicatieObjectArrayList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openDialog(position,exampleMedicatieObjectArrayList);

                //exampleMedicatieObjectArrayList.get(position).changeText1("clicked");
                //mAdapter.notifyItemChanged(position);
            }

            /*@Override
            public void onDeleteClick(int position) {
                removeItem(position);
                mAdapter.notifyItemChanged(position);
            }*/
        });
    }

    public void openDialog(final int position, final ArrayList<ExampleMedicatieObject> exampleMedicatieObjectArrayList)
    {
        new AlertDialog.Builder(this)
                .setTitle("Medication")
                .setMessage("Delete?")
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeItem(position, exampleMedicatieObjectArrayList);
                    }
                })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }
                ).create().show();


        //ExampleDialog exampleDialog = new ExampleDialog();
        //delete = exampleDialog.getDelete();
        //exampleDialog.show(getSupportFragmentManager(), "exampleDialog");
    }

}

