package edu.uga.cs.jobstracker;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.List;


/**
 * This is an activity controller class for listing the current job leads.
 * The current job leads are listed as a RecyclerView.
 */
public class ReviewJobLeadsActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "ReviewJobLeadsActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    private JobLeadsData jobLeadsData = null;
    private List<JobLead> jobLeadsList;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onCreate()" );

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_review_job_leads );

        recyclerView = (RecyclerView) findViewById( R.id.recyclerView );

        // use a linear layout manager for the recycler view
        layoutManager = new LinearLayoutManager(this );
        recyclerView.setLayoutManager( layoutManager );

        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activites may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        jobLeadsData = new JobLeadsData( this );

        // Execute the retrieval of the job leads in an asynchronous way,
        // without blocking the UI thread.
        new JobLeadDBReaderTask().execute();

    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of job leads, asynchronously.
    private class JobLeadDBReaderTask extends AsyncTask<Void, Void, List<JobLead>> {

        // This method will run as a background process to read from db.
        // It returns a list of retrieved JobLead objects.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onCreate callback (the job leads review activity is started).
        @Override
        protected List<JobLead> doInBackground( Void... params ) {
            jobLeadsData.open();
            jobLeadsList = jobLeadsData.retrieveAllJobLeads();

            Log.d( DEBUG_TAG, "JobLeadDBReaderTask: Job leads retrieved: " + jobLeadsList.size() );

            return jobLeadsList;
        }

        // This method will be automatically called by Android once the db reading
        // background process is finished.  It will then create and set an adapter to provide
        // values for the RecyclerView.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( List<JobLead> jobLeadsList ) {
            super.onPostExecute(jobLeadsList);
            recyclerAdapter = new JobLeadRecyclerAdapter( jobLeadsList );
            recyclerView.setAdapter( recyclerAdapter );
        }
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onResume()" );
        // open the database in onResume
        if( jobLeadsData != null )
            jobLeadsData.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onPause()" );
        // close the database in onPause
        if( jobLeadsData != null )
            jobLeadsData.close();
        super.onPause();
    }

    // These activity callback methods are not needed and are for edational purposes only
    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onRestart()" );
        super.onRestart();
    }
}
