package edu.uga.cs.jobstrackerrest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * This class is an activity to create a new job lead.
 */
public class NewJobLeadActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "NewJobLeadActivity";

    private EditText companyNameView;
    private EditText phoneView;
    private EditText urlView;
    private EditText commentsView;

    private JobLeadsData jobLeadsData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job_lead);

        Log.d( DEBUG_TAG, "onCreate()" );

        companyNameView = findViewById( R.id.editText1 );
        phoneView = findViewById( R.id.editText2 );
        urlView = findViewById( R.id.editText3 );
        commentsView = findViewById( R.id.editText4 );
        Button saveButton = findViewById(R.id.button);

        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activites may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        jobLeadsData = new JobLeadsData( this );

        saveButton.setOnClickListener( new ButtonClickListener()) ;
    }

    // Add this new JobLead.
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String companyName = companyNameView.getText().toString();
            String phone = phoneView.getText().toString();
            String url = urlView.getText().toString();
            String comments = commentsView.getText().toString();
            JobLead jobLead = new JobLead( companyName, phone, url, comments );

            // Store this new job lead in the RESTful Web Service asynchronously,
            // without blocking the UI thread.
            new StoreJobLeadTask().execute( jobLead );
        }
    }

    // An AsyncTask class (it extends AsyncTask) to perform POST on the RESTful Web Service asynchronously.
    private class StoreJobLeadTask extends AsyncTask<JobLead, JobLead> {

        // This method will run as a background process to run a POST request on the RESTful Web Service.
        @Override
        protected JobLead doInBackground( JobLead... jobLead ) {
            jobLeadsData.storeJobLead( jobLead[0] );
            return jobLead[0];
        }

        // This method will be automatically called by Android once the POST request is finished.
        @Override
        protected void onPostExecute( JobLead jobLead ) {
            if( jobLead != null ) {
                // Show a quick confirmation
                Toast.makeText(getApplicationContext(), "Job lead created for " + jobLead.getCompanyName(),
                        Toast.LENGTH_SHORT).show();

                // Clear the EditTexts for next use.
                companyNameView.setText("");
                phoneView.setText("");
                urlView.setText("");
                commentsView.setText("");

                Log.d(DEBUG_TAG, "Job lead saved: " + jobLead);
            }
            else
                Toast.makeText(getApplicationContext(), "Failed to save this job lead",
                        Toast.LENGTH_SHORT).show();
        }
    }

    // These callbacks are just for educational purposes.
    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "onResume()" );
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "onPause()" );
        super.onPause();
    }

    // These activity callback methods are not needed and are for edational purposes only
    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "onStart()" );
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "onRestart()" );
        super.onRestart();
    }
}
