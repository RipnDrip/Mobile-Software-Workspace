package edu.uga.cs.pushcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "Push Counter";

    private int counter = 0;
    private TextView countertView;
    private Button pushButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i( DEBUG_TAG, "MainActivity.onCreate(): Starting" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtain references to the Views
        countertView = findViewById( R.id.textView );
        pushButton = findViewById( R.id.button );

        // set the click listener for the button
        pushButton.setOnClickListener( new ButtonClickListener() );

        /*
        // This is an alternative way to set the button click listener
        // using an anonymous class
        pushButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                counter++;
                countertView.setText( "Pushes: " + counter );
            }
        } );

        // This is another way to set the button click listener
        // using a lambda expression
        // IMPORTANT: you will need to add a compileOptions section to your build.gradle (app)
        // file, as the last section in the   android { ... }   part.
        // Add these lines:
        //    compileOptions {
        //        sourceCompatibility JavaVersion.VERSION_1_8
        //        targetCompatibility JavaVersion.VERSION_1_8
        //    }
        //
        pushButton.setOnClickListener( view -> {
                counter++;
                countertView.setText( "Pushes: " + counter );
          } );

        */
    }

    // button click listener class
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick( View v ) {
            counter++;
            countertView.setText( "Pushes: " + counter );
        }
    }
    // These activity callback methods are not needed and are for educational purposes only
    @Override
    protected void onStart() {
        Log.i( DEBUG_TAG, "MainActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i( DEBUG_TAG, "MainActivity.onResume()" );
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i( DEBUG_TAG, "MainActivity.onPause()" );
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i( DEBUG_TAG, "MainActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i( DEBUG_TAG, "MainActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i( DEBUG_TAG, "MainActivity.onRestart()" );
        super.onRestart();
    }
}