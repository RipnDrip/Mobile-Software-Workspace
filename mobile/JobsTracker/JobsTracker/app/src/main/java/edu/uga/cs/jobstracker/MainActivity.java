package edu.uga.cs.jobstracker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The main activity class.  It just sets listeners for the two buttons.
 */
public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_TYPE = "edu.uga.cs.jobstracker.JOBLEADS_DATA";

    private Button newLeadButton;
    private Button reviewLeadsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newLeadButton = findViewById( R.id.button1 );
        reviewLeadsButton = findViewById( R.id.button2 );

        newLeadButton.setOnClickListener( new NewLeadButtonClickListener() );
        reviewLeadsButton.setOnClickListener( new ReviewLeadsButtonClickListener() );
    }

    private class NewLeadButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), NewJobLeadActivity.class);
            view.getContext().startActivity( intent );
        }
    }

    private class ReviewLeadsButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ReviewJobLeadsActivity.class);
            view.getContext().startActivity(intent);
        }
    }
}
