package edu.uga.cs.topcompaniesfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompanyListStartActivity extends AppCompatActivity {

    private Button listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list_start_main);

        listButton = findViewById(R.id.companyListButton);
        listButton.setOnClickListener(new ButtonClickListener(){

        });
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), CompanyListMainActivity.class);
            startActivity(intent);
        }
    }
}