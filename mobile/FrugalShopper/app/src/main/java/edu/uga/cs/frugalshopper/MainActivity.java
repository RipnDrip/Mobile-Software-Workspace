package edu.uga.cs.frugalshopper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import edu.uga.cs.frugalshopper.Item;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "Frugal Shopper";
    private Button compareButton;
    private EditText item1Price;
    private EditText item1Pounds;
    private EditText item1Ounces;
    private EditText item2Price;
    private EditText item2Pounds;
    private EditText item2Ounces;
    private EditText item3Price;
    private EditText item3Pounds;
    private EditText item3Ounces;
    private TextView result;
    private Item item1;
    private Item item2;
    private Item item3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compareButton = (Button) findViewById( R.id.compareButton );
        item1Price = (EditText) findViewById( R.id.editTextPriceItem1 );
        item1Pounds = (EditText) findViewById( R.id.editTextPoundsItem1 );
        item1Ounces = (EditText) findViewById( R.id.editTextOuncesItem1 );
        item2Price = (EditText) findViewById( R.id.editTextPriceItem2 );
        item2Pounds = (EditText) findViewById( R.id.editTextPoundsItem2 );
        item2Ounces = (EditText) findViewById( R.id.editTextOuncesItem2 );
        item3Price = (EditText) findViewById( R.id.editTextPriceItem3 );
        item3Pounds = (EditText) findViewById( R.id.editTextPoundsItem3 );
        item3Ounces = (EditText) findViewById( R.id.editTextOuncesItem3 );
        result = (TextView) findViewById( R.id.result );
        compareButton.setOnClickListener( new ButtonClickListener() );
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick( View v ) {
            if(checkIfValid()){
                item1 = setItem(item1Price, item1Pounds, item1Ounces);
                item2 = setItem(item2Price, item2Pounds, item2Ounces);
                item3 = setItem(item3Price, item3Pounds, item3Ounces);

                double ppo1 = ((item1.getPounds() * 16 ) + item1.getOunces()) / item1.getPrice();
                double ppo2 = ((item2.getPounds() * 16 ) + item2.getOunces()) / item2.getPrice();
                double ppo3 = ((item3.getPounds() * 16 ) + item3.getOunces()) / item3.getPrice();
                if (Double.isNaN(ppo1)){
                    ppo1 = 0;
                }
                if (Double.isNaN(ppo2)){
                    ppo2 = 0;
                }
                if (Double.isNaN(ppo3)){
                    ppo3 = 0;
                }
                System.out.println(ppo1 + " per ounce");
                System.out.println(ppo2 + " per ounce");
                System.out.println(ppo3 + " per ounce");
                declareWinner(ppo1, ppo2, ppo3);
            }
        }

        public void declareWinner(double ppo1, double ppo2, double ppo3){
            if ((ppo1 > ppo2) && (ppo1 > ppo3)){
                result.setText("Item 1");
            }
            else if ((ppo2 > ppo1) && (ppo2 > ppo3)){
                result.setText("Item 2");
            }
            else if ((ppo3 > ppo1) && (ppo3 > ppo2)){
                result.setText("Item 3");
            }
            else {
                result.setText("Equal value!");
            }
        }

        public Item setItem(EditText price, EditText pounds, EditText ounces) {
            Item item;
            double newPrice = (price.getText().toString().matches("") ? 0 : Double.parseDouble(price.getText().toString()));
            int newPounds = (pounds.getText().toString().matches("") ? 0 : Integer.parseInt(pounds.getText().toString()));
            int newOunces = (ounces.getText().toString().matches("") ? 0 : Integer.parseInt(ounces.getText().toString()));
            item = new Item(newPrice, newPounds, newOunces);
            return item;
        }

        public boolean checkIfValid(){
            boolean valid = true;
            Toast toast;
            if(item1Price.getText().toString().matches("") && item2Price.getText().toString().matches("") && item3Price.getText().toString().matches("") &&
                    item1Pounds.getText().toString().matches("") && item2Pounds.getText().toString().matches("") && item2Pounds.getText().toString().matches("") &&
                    item1Ounces.getText().toString().matches("") && item3Ounces.getText().toString().matches("") && item3Ounces.getText().toString().matches("")){
                toast = Toast.makeText(getBaseContext(), "You must enter information for at least one item!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                valid = false;
            }
            else if(item1Price.getText().toString().matches("") && item2Price.getText().toString().matches("") && item3Price.getText().toString().matches("")){
                toast = Toast.makeText(getBaseContext(), "You must enter a price for at least one item!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                valid = false;
            }
            else if(!item1Price.getText().toString().matches("") && (item1Pounds.getText().toString().matches("") && item1Ounces.getText().toString().matches("")) ||
                    !item2Price.getText().toString().matches("") && (item2Pounds.getText().toString().matches("") && item2Ounces.getText().toString().matches("")) ||
                    !item3Price.getText().toString().matches("") && (item3Pounds.getText().toString().matches("") && item3Ounces.getText().toString().matches(""))){
                toast = Toast.makeText(getBaseContext(), "You must enter weight information where there is a price!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                valid = false;
            }
            else if((!item1Pounds.getText().toString().matches("") || !item1Ounces.getText().toString().matches("")) && item1Price.getText().toString().matches("") ||
                    (!item2Pounds.getText().toString().matches("") || !item2Ounces.getText().toString().matches("")) && item2Price.getText().toString().matches("") ||
                    (!item3Pounds.getText().toString().matches("") || !item3Ounces.getText().toString().matches("")) && item3Price.getText().toString().matches("")){
                toast = Toast.makeText(getBaseContext(), "You must enter a price where there is weight information!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                valid = false;
            }
            return valid;
        }

    }
}