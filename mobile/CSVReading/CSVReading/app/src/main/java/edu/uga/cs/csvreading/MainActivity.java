package edu.uga.cs.csvreading;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;



/** A simple app to illustrate reading from a CSV file and
 * constructing a TableLayout programmatically.
 *
 * Please note that the dependencies in the build.gradle (Module: app) file must
 * include a directive
 *     implementation 'com.opencsv:opencsv:4.2'
 * to provide the necessary CSV library.
 */
public class MainActivity extends AppCompatActivity {

    final String TAG = "CSVReading";

    private Button rankingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rankingButton = (Button) findViewById( R.id.button );
        rankingButton.setOnClickListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                TableLayout tableLayout = (TableLayout) findViewById(R.id.table_main);
                Resources res = getResources();
                InputStream in_s = res.openRawResource( R.raw.data );

                // set up margins for each TextView in the table layout
                android.widget.TableRow.LayoutParams layoutParams =
                        new TableRow.LayoutParams( TableRow.LayoutParams.WRAP_CONTENT,
                                                   TableRow.LayoutParams.WRAP_CONTENT );
                layoutParams.setMargins(20, 0, 20, 0);

                // read the CSV data
                CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
                String [] nextLine;
                while( ( nextLine = reader.readNext() ) != null ) {

                    // nextLine[] is an array of values from the line

                    // create the next table row for the layout
                    TableRow tableRow = new TableRow( getBaseContext() );
                    for( int i = 0; i < nextLine.length; i++ ) {

                        // create a new TextView and set its text
                        TextView textView = new TextView( getBaseContext() );
                        textView.setText( nextLine[i] );

                        // add the new TextView to the table row in the table supplying the
                        // layout parameters
                        tableRow.addView( textView, layoutParams );
                    }

                    // add the next row to the table layout
                    tableLayout.addView( tableRow );
                }
            } catch (Exception e) {
                Log.e( TAG, e.toString() );
            }
        }
    }
}