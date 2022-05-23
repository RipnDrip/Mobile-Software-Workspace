package edu.uga.cs.jobstrackerrest;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * This class is an abstraction representing job leads stored in the cloud database.
 */
public class JobLeadsData {

    public static final String DEBUG_TAG = "JobLeadsData";

    // Web API endpoint
    public static final String BASE_URL = "http://uml.cs.uga.edu:8080/jobtracker/rest/";

    Retrofit retrofit;
    JobLeadsService service;

    public JobLeadsData( Context context ) {
        // Build a Retrofit service
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                // add a JSON to Java and Java to JSON converter
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        // Create a new service instance
        service = retrofit.create( JobLeadsService.class );
        Log.d( DEBUG_TAG, "JobLeadsData: service: " + service );
    }

    // Web API (service) interface.  Handled requests:
    //
    // GET /joblead               - retrieve all JobLeads
    // POST /joblead + JSON       - create a new JobLead resource
    // PUT /joblead/{id} + JSON   - update an existing JobLead resource
    // DELETE /joblead/{id}       - delete an existing JobLead resource
    //
    private interface JobLeadsService {

        @GET("joblead")
        Call<List<JobLead>> retrieveAllJobLeads();

        @POST("joblead")
        Call<Void> storeJobLead( @Body JobLead jobLead );

        @PUT("joblead/{id}")
        Call<Void> updateJobLead( @Path("id") long itemId, @Body JobLead jobLead );

        @DELETE("joblead/{id}")
        Call<Void> deleteJobLead( @Path("id") long itemId );
    }

    // Retrieve all job leads as a List.
    // This is how we restore persistent objects stored as rows in the job leads table in the database.
    // For each retrieved row, we create a new JobLead (Java object) instance and add it to the list.
    public List<JobLead> retrieveAllJobLeads() {
        Log.d( DEBUG_TAG, "retrieveAllJobLeads: start" );

        List<JobLead> jobLeads = null;
        Call<List<JobLead>> jobLeadsCall = service.retrieveAllJobLeads();

        try {
            jobLeads = jobLeadsCall.execute().body();
            Log.d( DEBUG_TAG, "retrieveAllJobLeads: jobLeads: " + jobLeads );
        }
        catch( IOException e ) {
            Log.e( DEBUG_TAG, e.toString() );
            return null;
        }

        Log.d( DEBUG_TAG, "retrieveAllJobLeads: Number of records received: " + jobLeads.size() );

        return jobLeads;
    }

    // Store a new job lead in the database
    public JobLead storeJobLead( JobLead jobLead ) {

        long id = -1;

        Call<Void> jobLeadCall = service.storeJobLead( jobLead );
        try {
            // Obtain the "raw", i.e., HTTP response from the service
            // We will log the location of the created resource
            Response response = jobLeadCall.execute().raw();
            String uri = response.header( "Location" );
            int lastSlash = uri.lastIndexOf( '/' );
            if( lastSlash >= 0 ) {
                String newId = uri.substring(lastSlash + 1);
                id = Long.parseLong( newId );
                jobLead.setId( id );
            }

            Log.d( DEBUG_TAG, "storeJobLead: created job lead; uri: " + uri );
            Log.d( DEBUG_TAG, "storeJobLead: created job lead; id: " + id );
        }
        catch( IOException e ) {
            Log.e( DEBUG_TAG, e.toString() );
            return null;
        }
        catch( NumberFormatException e ) {
            Log.e( DEBUG_TAG, "Created URI identifier after POST is not a number." );
            Log.e( DEBUG_TAG, e.toString() );
        }

        return jobLead;
    }

    // Store a new job lead in the database
    public JobLead updateJobLead( JobLead jobLead ) {

        Log.d( DEBUG_TAG, "updating id: " + jobLead.getId() );

        Call<Void> jobLeadCall = service.updateJobLead( jobLead.getId(), jobLead );
        try {
            // Obtain the "raw", i.e., HTTP response from the service
            // We will log the location of the created resource
            Response response = jobLeadCall.execute().raw();
            //Log.d( DEBUG_TAG, "storeJobLead: updated job lead; uri: " + response.header( "Location" ) );
        }
        catch( IOException e ) {
            Log.e( DEBUG_TAG, e.toString() );
            return null;
        }

        return jobLead;
    }

    // Store a new job lead in the database
    public JobLead deleteJobLead( JobLead jobLead ) {

        Log.d( DEBUG_TAG, "deleting id: " + jobLead.getId() );

        Call<Void> jobLeadCall = service.deleteJobLead( jobLead.getId() );
        try {
            // Obtain the "raw", i.e., HTTP response from the service
            // We will log the location of the created resource
            Response response = jobLeadCall.execute().raw();
            //Log.d( DEBUG_TAG, "deleteJobLead: deleted job lead; uri: " + response.header( "Location" ) );
        }
        catch( IOException e ) {
            Log.e( DEBUG_TAG, e.toString() );
            return null;
        }

        return jobLead;
    }
}
