package edu.uga.cs.jobstrackerrest;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * The main activity class.  It just sets listeners for the two buttons.
 */
public class MainActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d( DEBUG_TAG, "onCreate()" );

        Button newLeadButton = findViewById(R.id.button1);
        Button reviewLeadsButton = findViewById(R.id.button2);

        newLeadButton.setOnClickListener(new NewLeadButtonClickListener());
        reviewLeadsButton.setOnClickListener(new ReviewLeadsButtonClickListener());
    }

    private static class NewLeadButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), NewJobLeadActivity.class);
            view.getContext().startActivity( intent );
        }
    }

    private static class ReviewLeadsButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ReviewJobLeadsActivity.class);
            view.getContext().startActivity(intent);
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
