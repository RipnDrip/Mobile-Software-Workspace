package edu.uga.cs.topcompaniesfragments;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * A simple activity to show Companies utilizing fragments.
 */
public class CompanyListMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this call will create the UI based on the screen in portrait orientation.
        // /res/layout/activity_company_list_main.xml will be used;
        // in landscape orientation /res/layout-land/activity_company_list_main.xml will be used
        setContentView( R.layout.activity_company_list_main);
    }
}
