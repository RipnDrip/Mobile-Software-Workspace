package edu.uga.cs.topcompanies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent i = getIntent();
        int company = i.getIntExtra("company", 0);

        TextView title = (TextView) findViewById(R.id.textView5);
        ImageView logo = (ImageView) findViewById(R.id.imageView2);
        TextView overview = (TextView) findViewById( R.id.textView8);

        Resources res = getResources();
        InputStream in;
        byte[] b;
        Drawable drawable;
        switch (company){
            case(0) : {
                title.setText("Vanguard");
                in = res.openRawResource( R.raw.vanov );
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    overview.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                drawable  = getResources().getDrawable(R.drawable.van);
                break;
            }
            case(1) : {
                title.setText("Goldman Sachs");
                in = res.openRawResource( R.raw.gsov );
                drawable  = getResources().getDrawable(R.drawable.gs);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    overview.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case(2) : {
                title.setText("American Express");
                in = res.openRawResource( R.raw.amexov);
                drawable  = getResources().getDrawable(R.drawable.amex);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    overview.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case(3) : {
                title.setText("Citi Bank");
                in = res.openRawResource( R.raw.citiov);
                drawable  = getResources().getDrawable(R.drawable.citi);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    overview.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case(4) : {
                title.setText("Bank of America");
                in = res.openRawResource( R.raw.boaov);
                drawable  = getResources().getDrawable(R.drawable.boa);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    overview.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default: {
                drawable = null;
            }
        }

        final Button back = findViewById(R.id.button2);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new  Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        logo.setImageDrawable(drawable);
    }
}