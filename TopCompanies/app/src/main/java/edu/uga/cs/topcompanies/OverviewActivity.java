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
                title.setText("Microsoft");
                in = res.openRawResource( R.raw.microsoftoverview);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    overview.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                drawable  = getResources().getDrawable(R.drawable.microsoft);
                break;
            }
            case(1) : {
                title.setText("LinkedIn");
                in = res.openRawResource( R.raw.linkedinoverview);
                drawable  = getResources().getDrawable(R.drawable.linkedin);
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
                title.setText("Apple");
                in = res.openRawResource( R.raw.appleoverview);
                drawable  = getResources().getDrawable(R.drawable.apple);
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
                title.setText("Google");
                in = res.openRawResource( R.raw.googleoverview);
                drawable  = getResources().getDrawable(R.drawable.google);
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
                title.setText("Facebook");
                in = res.openRawResource( R.raw.facebookoverview);
                drawable  = getResources().getDrawable(R.drawable.facebook);
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