package edu.uga.cs.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById( R.id.button );
        listView = findViewById( R.id.listview );
        button.setOnClickListener( new ButtonClickListener() );
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            List<String> states = new ArrayList<String>();

            try {
                // Gain access to the app's resources
                Resources res = getResources();
                // Open a raw resource (a file) for reading and read it's content into a byte array
                InputStream ins = res.openRawResource( R.raw.states );
                BufferedReader reader = new BufferedReader( new InputStreamReader( ins ) );
                String state;
                String capital;
                String line;
                int i = 0;
                // Read one line at a time and populate the states list
                while( i < 50 && (line = reader.readLine()) != null ) {
                    StringTokenizer tokenizer = new StringTokenizer( line, ",");
                    state = tokenizer.nextToken();
                    capital = tokenizer.nextToken();
                    states.add( state + " has capital in: " + capital );
                    i++;
                }
            } catch (Exception e) {
                Toast toast = Toast.makeText( getApplicationContext(),
                        "Can't read the states file",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
            // Create an ArrayAdapter initialized with the state+capital strings
            // android.R.layout.simple_list_item_1 is a layout for one simple list item
            // as a TextView.  More information at https://developer.android.com/reference/android/R.layout
            ArrayAdapter<String> itemsAdapter =
                    new ArrayAdapter<String>( v.getContext(), android.R.layout.simple_list_item_1, states );
            // Set the adapter for the ListView to show it
            listView.setAdapter( itemsAdapter );
        }
    }
}