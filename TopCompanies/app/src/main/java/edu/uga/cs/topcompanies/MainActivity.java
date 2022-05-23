package edu.uga.cs.topcompanies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button overview = findViewById(R.id.button5);
        final Button details = findViewById(R.id.button6);

        String[] companyList = {
                "Apple", "Facebook", "Google", "LinkedIn", "Microsoft"
        };

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, companyList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Selection: "+ spinner.getSelectedItemPosition());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new  Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("company", spinner.getSelectedItemPosition());
                startActivity(intent);
            }
        });

        overview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new  Intent(v.getContext(), OverviewActivity.class);
                intent.putExtra("company", spinner.getSelectedItemPosition());
                startActivity(intent);
            }
        });

    }
}