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
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        countertView = (TextView) findViewById( R.id.textView );
        pushButton = (Button) findViewById( R.id.button );
        pushButton.setOnClickListener( new ButtonClickListener() );
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick( View v ) {
            counter++;
            countertView.setText( "Pushes: " + counter );
        }
    }
}