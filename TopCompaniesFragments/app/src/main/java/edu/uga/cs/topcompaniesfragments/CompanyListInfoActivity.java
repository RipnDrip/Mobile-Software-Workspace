package edu.uga.cs.topcompaniesfragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * A simple activity to show Companies utilizing fragments.
 */
public class CompanyListInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );

        // IMPORTANT:
        // Add the back button in the ActionBar of this activity.  It will appear
        // as a left chevron (arrow tip), the regular back button.
        //
        // We can't use the Parent specification in the AndroidManifest, since
        // it would place the activity on the back stack.  Consequently, when going
        // back to the screen with the list of Company List (using the "parent-specified"
        // back button, this would cause a recreation of the ListFragment.  The new list
        // would show the initial item (Android version) as selected, not the one we
        // actually selected when transitioning to the Company List details screen.
        //
        // However, there must be a listener added for this back button (look below).
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // If this activity is in foreground in portrait orientation, the CompanyListStartActivity is also present
        // in on the back stack.  When the user changes the orientation to landscape, Android kills
        // ALL activities in the app and restarts them. All fragments hosted in them are killed, as well.
        // This activity (CompanyListInfoActivity) will be restarted, but it will no be needed, as
        // the CompanyListMainActivity's landscape orientation will include all needed data (both
        // fragments).  CompanyListMainActivity will also be restarted and it will build and show
        // both fragments.  CompanyListFragment hosted there will control this work.
        // Consequently, this activity will not be needed after the restart.  So, in landscape orientation
        // we should kill this activity.  It is done using the finish() method.  Note that we immediately
        // return from onCreate(), as well.
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            finish();
            return;
        }

        CompanyListInfoFragment companyListInfoFragment = new CompanyListInfoFragment();

        // pass on any saved data, i.e., Company List no (list index)
        companyListInfoFragment.setArguments( getIntent().getExtras() );

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // add the fragment within a transaction
        // android.R.id.content is used to obtain the view reference without having to have its id
        // it reference the root (ViewGroup) of the entire content area of an Activity
        getSupportFragmentManager().beginTransaction().replace( android.R.id.content, companyListInfoFragment).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Implement Back button listener method.
        // This method may be used for other actions from the ActionBar menu, if provided in the layout.
        int id = item.getItemId();
        // android.R.id.home is the built-in designation of the back button widget.
        if( id == android.R.id.home ) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected( item );
    }


}
