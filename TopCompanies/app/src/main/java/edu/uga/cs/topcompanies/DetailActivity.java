package edu.uga.cs.topcompanies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class DetailActivity extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        int company = i.getIntExtra("company", 0);

        TextView title = (TextView) findViewById(R.id.textView3);
        TextView details = (TextView) findViewById( R.id.textView7);
        ImageView logo = (ImageView) findViewById(R.id.imageView);
        Resources res = getResources();
        InputStream in;
        byte[] b;
        Drawable drawable;
        switch (company){
            case(0) : {
                title.setText("Microsoft");
                in = res.openRawResource( R.raw.microsoftdetails);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    details.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                drawable  = getResources().getDrawable(R.drawable.microsoft);
                break;
            }
            case(1) : {
                title.setText("LinkedIn");
                in = res.openRawResource( R.raw.linkedindetails);
                drawable  = getResources().getDrawable(R.drawable.linkedin);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    details.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case(2) : {
                title.setText("Apple");
                in = res.openRawResource( R.raw.appledetails);
                drawable  = getResources().getDrawable(R.drawable.apple);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    details.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case(3) : {
                title.setText("Google");
                in = res.openRawResource( R.raw.googledetails);
                drawable  = getResources().getDrawable(R.drawable.google);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    details.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case(4) : {
                title.setText("Facebook");
                in = res.openRawResource( R.raw.facebookdetails);
                drawable  = getResources().getDrawable(R.drawable.facebook);
                try {
                    b = new byte[ in.available() ];
                    in.read(b);
                    details.setText( new String(b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default: {
                drawable = null;
            }
        }

        final Button back = findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new  Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        logo.setImageDrawable(drawable);

    }
}
