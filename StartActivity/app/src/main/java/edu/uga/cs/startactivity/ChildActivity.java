package edu.uga.cs.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        TextView textView = findViewById( R.id.textView );
        Intent intent = getIntent();
        String message = intent.getStringExtra( MainActivity.MESSAGE_TYPE );

        if( message == null )
            textView.setText( "No message received" );
        else
            textView.setText( message );
    }
}