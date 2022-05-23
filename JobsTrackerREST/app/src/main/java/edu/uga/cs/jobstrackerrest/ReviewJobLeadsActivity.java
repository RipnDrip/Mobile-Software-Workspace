package edu.uga.cs.jobstrackerrest;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * This is an activity controller class for listing the current job leads.
 * Also, a new JobLead may be added, or an existing one updated or deleted.
 * The current job leads are listed in a RecyclerView.
 * JobLead additions are handled by the AddJobLeadDialogFragment.
 * JobLead updates (modifications or deletions) are handled by the EditJobLeadDialogFragment.
 * All operations on JobLeads are handled asynchronously by suitable AsyncTask-derived classes.
 */
public class ReviewJobLeadsActivity
        extends AppCompatActivity
        implements AddJobLeadDialogFragment.AddJobLeadDialogListener,
                   EditJobLeadDialogFragment.EditJobLeadDialogListener {

    public static final String DEBUG_TAG = "ReviewJobLeadsActivity";

    private RecyclerView recyclerView;
    private JobLeadRecyclerAdapter recyclerAdapter;

    // this will be an access object to the external RESTful Web Service
    private JobLeadsData jobLeadsData = null;

    // This is the internal list of job leads.
    // It should mirror the job leads in the external RESTful Web Service.
    // additions/deletions/updates are done on this list and on the Web Service
    // The RecyclerAdapter uses this internal list as the model data.
    private List<JobLead> jobLeadsList = null;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        Log.d( DEBUG_TAG, "onCreate()" );

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_review_job_leads );

        recyclerView = findViewById( R.id.recyclerView );
        FloatingActionButton floatingButton = findViewById(R.id.floatingActionButton);

        floatingButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new AddJobLeadDialogFragment();
                newFragment.show( getSupportFragmentManager(), null);

                //showDialogFragment(newFragment);
            }
        });

        // Set a linear layout manager for the recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the list of JobLeads
        jobLeadsList = new ArrayList<JobLead>();

        // Create a RecyclerAdapter
        recyclerAdapter = new JobLeadRecyclerAdapter( jobLeadsList, this );

        // Set the adapter for the RecyclerView
        // Note that the list empty initially and no job leads will be displayed.
        // The list will be update in the onPostExecute callback of the RetrieveJobLeadsTask
        // AsyncTask class for retrieving the list of jobs from the RESTful service.
        recyclerView.setAdapter( recyclerAdapter );

        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activites may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        jobLeadsData = new JobLeadsData( this );

        // Execute the retrieval of the job leads in an asynchronous way,
        // without blocking the main UI thread.
        //
        // Again, note that the list of JobLeads in the RecyclerAdapter is empty now
        // and the RecyclerView shows no items, initially.  However, the
        // list will be updated in the onPostExecute callback of the RetrieveJobLeadsTask,
        // once the list of JobLeads is received asynchronously.  Then, the RecyclerView
        // will be notified that the data has changed to update the RecyclerView.
        new RetrieveJobLeadsTask().execute();
    }

    // An AsyncTask class to retrieve all JobLeads asynchronously.
    public class RetrieveJobLeadsTask extends AsyncTask<Void, List<JobLead>> {

        // This method will run as a background process to read from the RESTful Web Service.
        @Override
        protected List<JobLead> doInBackground( Void... params ) {
            List<JobLead> jobLeadsList = jobLeadsData.retrieveAllJobLeads();

            Log.d( DEBUG_TAG, "RetrieveJobLeadTask: JobLeads retrieved: " + jobLeadsList.size() );

            return jobLeadsList;
        }

        // This method will be automatically called by Android once the JobLeads are received
        // and the background process is finished.  It will then update the RecyclerAdapter to provide
        // values for the RecyclerView.
        @Override
        protected void onPostExecute( List<JobLead> jList ) {
            if( jobLeadsList != null ) {
                Log.d( DEBUG_TAG, "jList.size(): " + jList.size() );

                // add all of the retrieved job leads to the internal list of job leads
                jobLeadsList.addAll(jList);

                // update the recycler adapter to the retrieved job leads
                recyclerAdapter.notifyDataSetChanged();
            }
            else
                Toast.makeText( getApplicationContext(), "No LobLeads retrieved from server.",
                        Toast.LENGTH_SHORT).show();        }
    }

    // An AsyncTask class to store a JobLead asynchronously.
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

                // Add the new job lead to the internal list of job leads
                jobLeadsList.add(jobLead);

                // Update the recycler view to include the new job lead
                recyclerAdapter.notifyItemInserted(jobLeadsList.size() - 1);

                Log.d(DEBUG_TAG, "Job lead saved: " + jobLead);
            }
            else
                Toast.makeText(getApplicationContext(), "Failed to save this job lead",
                        Toast.LENGTH_SHORT).show();
        }
    }

    // An AsyncTask class to update a JobLead asynchronously.
    private class UpdateJobLeadTask extends AsyncTask<JobLead, JobLead> {

        private int position;  // we need the position of the job lead being updated.

        public UpdateJobLeadTask( int position ) {
            Log.d( DEBUG_TAG, "New UpdateJobLeadTask for position: " + position );
            this.position = position;
        }

        // This method will run as a background process to run a PUT request on the RESTful Web Service.
        @Override
        protected JobLead doInBackground( JobLead... jobLead ) {
            jobLeadsData.updateJobLead( jobLead[0] );
            return jobLead[0];
        }

        // This method will be automatically called by Android once the PUT request is finished.
        @Override
        protected void onPostExecute( JobLead jobLead ) {
            if( jobLead != null ) {
                // Show a quick confirmation
                Toast.makeText(getApplicationContext(), "Job lead updated for " + jobLead.getCompanyName(),
                        Toast.LENGTH_SHORT).show();

                // Update the recycler view to show the changes in the updated job lead in that view
                recyclerAdapter.notifyItemChanged( position );

                Log.d(DEBUG_TAG, "Job lead updated: " + jobLead);
            }
            else
                Toast.makeText(getApplicationContext(), "Failed to update this job lead",
                        Toast.LENGTH_SHORT).show();
        }
    }

    // An AsyncTask class to delete a JobLead asynchronously.
    private class DeleteJobLeadTask extends AsyncTask<JobLead, JobLead> {

        private int position;  // we need the position of the job lead being deleted.

        public DeleteJobLeadTask( int position ) {
            Log.d( DEBUG_TAG, "New DeleteJobLeadTask for position: " + position );
            this.position = position;
        }

        // This method will run as a background process to run a DELETE request on the RESTful Web Service.
        @Override
        protected JobLead doInBackground( JobLead... jobLead ) {
            jobLeadsData.deleteJobLead( jobLead[0] );
            return jobLead[0];
        }

        // This method will be automatically called by Android once the DELETE request is finished.
        @Override
        protected void onPostExecute( JobLead jobLead ) {
            if( jobLead != null ) {
                // Show a quick confirmation
                Toast.makeText(getApplicationContext(), "Job lead deleted for " + jobLead.getCompanyName(),
                        Toast.LENGTH_SHORT).show();

                // remove the deleted job lead from the list (internal list in the App)
                jobLeadsList.remove( position );

                // Update the recycler view to remove the deleted job lead from that view
                recyclerAdapter.notifyItemRemoved( position );

                Log.d(DEBUG_TAG, "Job lead deleted: " + jobLead);
            }
            else
                Toast.makeText(getApplicationContext(), "Failed to delete this job lead",
                        Toast.LENGTH_SHORT).show();
        }
    }

    // This is our own callback for a DialogFragment which adds a new job lead.
    // It is called from the AddJobLeadDialogFragment.
    public void onFinishAddJobLeadDialog(JobLead jobLead) {
        // now add the new job lead in the external service
        // the on PostExecute will also add the new job lead to the internal list
        // and update the recycler view
        new StoreJobLeadTask().execute( jobLead );
    }

    // This is our own callback for a DialogFragment which edits an existing JobLead.
    // The edit may be an update or a deletion of this JobLead.
    // It is called from the EditJobLeadDialogFragment.
    public void onFinishEditJobLeadDialog( int position, JobLead jobLead, int action ) {
        if( action == EditJobLeadDialogFragment.SAVE ) {
            JobLead currentJobLead = jobLeadsList.get(position);

            Log.d( DEBUG_TAG, "Updating job lead at: " + position + "(" + currentJobLead.getCompanyName() + ")" );

            // update the job lead on the internal job lead list
            currentJobLead.setCompanyName(jobLead.getCompanyName());
            currentJobLead.setPhone(jobLead.getPhone());
            currentJobLead.setUrl(jobLead.getUrl());
            currentJobLead.setComments(jobLead.getComments());

            // now update the job lead in the external service
            // the on PostExecute will also update the recycler view
            new UpdateJobLeadTask( position ).execute(currentJobLead);
        }
        else if( action == EditJobLeadDialogFragment.DELETE ) {
            JobLead currentJobLead = jobLeadsList.get(position);

            Log.d( DEBUG_TAG, "Deleting job lead at: " + position + "(" + currentJobLead.getCompanyName() + ")" );

            // now delete the job lead in the external service
            // the on PostExecute will also remove the job lead from the internal list
            // and update the recycler view
            new DeleteJobLeadTask( position ).execute(currentJobLead);
        }
    }

    void showDialogFragment( DialogFragment newFragment ) {
        newFragment.show( getSupportFragmentManager(), null);
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
