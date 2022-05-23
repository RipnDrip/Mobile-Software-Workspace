package edu.uga.cs.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_TYPE = "Simple Message";
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById( R.id.button );
        startButton.setOnClickListener( new ButtonClickListener() );
    }

    private class ButtonClickListener implements
            View.OnClickListener
    {
        @Override
        public void onClick( View view ) {
            Intent intent = new
                    Intent( view.getContext(), ChildActivity.class );
            String message = "Hello from parent";
            intent.putExtra( MESSAGE_TYPE, message );
            startActivity( intent );
        }
    }
}