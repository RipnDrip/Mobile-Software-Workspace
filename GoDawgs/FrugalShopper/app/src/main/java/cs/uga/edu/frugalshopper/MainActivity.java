package cs.uga.edu.frugalshopper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOError;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "FrugalShopper";

    private TextView textViewPriceA;
    private TextView textViewPriceB;
    private TextView textViewPriceC;

    private TextView textViewPoundA;
    private TextView textViewPoundB;
    private TextView textViewPoundC;

    private TextView textViewOzA;
    private TextView textViewOzB;
    private TextView textViewOzC;


    private double unitPriceA = 0.0;
    private double unitPriceB = 0.0;
    private double unitPriceC = 0.0;

    private double getPriceA = 0.0;
    private double getPriceB = 0.0;
    private double getPriceC = 0.0;

    private int getPoundA = 0;
    private int getPoundB = 0;
    private int getPoundC = 0;

    private int getOzA = 0;
    private int getOzB = 0;
    private int getOzC = 0;


    private TextView frugalBuyOutput;
    private Button calculateFrugalBuy;


    /* Create Main Activity class for application */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(DEBUG_TAG,"onCreate() method of the MainActivity Class");

        setContentView(R.layout.activity_main);

        /* Sets views for input*/
        textViewPriceA = (TextView) findViewById(R.id.priceA);
        textViewPriceB = (TextView) findViewById(R.id.priceB);
        textViewPriceC = (TextView) findViewById(R.id.priceC);

        textViewPoundA = (TextView) findViewById(R.id.wtPoundA);
        textViewPoundB = (TextView) findViewById(R.id.wtPoundB);
        textViewPoundC = (TextView) findViewById(R.id.wtPoundC);

        textViewOzA = (TextView) findViewById(R.id.wtOzA);
        textViewOzB = (TextView) findViewById(R.id.wtOzB);
        textViewOzC = (TextView) findViewById(R.id.wtOzC);

        /* set views for output*/
        frugalBuyOutput = (TextView) findViewById(R.id.frugalBuy);
        calculateFrugalBuy = (Button) findViewById(R.id.calculateFrugalBuyButton);
        calculateFrugalBuy.setOnClickListener(new ButtonClickListener());

    }

    /*Converts string into a double */
    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            }catch (NumberFormatException ne){
                return -1;
            }
            catch(Exception e) {
                return -1;
            }
        }
        else return 0;
    }

    /*Converts string into an int */
    double ParseInt(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Integer.parseInt(strNumber);
            } catch (NumberFormatException ne){
                return -1;
            }
            catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function valid-
                // ates field at initial point
            }
        }
        else return 0;
    }

    /*Main operation to find minimum price*/
    String findFrugalBuy(double priceA, double priceB, double priceC) {
        String output = " ";
        Double[] unitPrices = {priceA,priceB,priceC};
        double min = Double.MAX_VALUE;
        int option = Integer.MAX_VALUE;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        /*Compares prices between products */
        for(int i=0;i<unitPrices.length;i++){
            if(min >= unitPrices[i] && unitPrices[i] != 0.0){
                unitPrices[i] = ParseDouble(df.format(unitPrices[i]));
                min = unitPrices[i];
                option = i;
            }
        }

        switch (option){
            case 0:
                output = "Buy Product A";
                break;
            case 1:
                output = "Buy Product B";
                break;
            case 2:
                output = "Buy Product C";
                break;
            default:
                output = " ";
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Try again ! !",
                        Toast.LENGTH_SHORT);

                toast.show();
        }

        return output;
    }

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            frugalBuyOutput.setText(" ");

            try {

                if((!textViewPriceA.getText().equals(null)) && (!textViewPoundA.getText().equals(null))
                        && (!textViewOzA.getText().equals(null)) ){

                    getPriceA = ParseDouble(textViewPriceA.getText().toString());
                    getPoundA = (int) ParseInt(textViewPoundA.getText().toString());
                    getOzA = (int) ParseInt(textViewOzA.getText().toString());

                    try {
                        unitPriceA = getPriceA / ((getPoundA * 16) + getOzA);
                        if (Double.isNaN(unitPriceA) || Double.isInfinite(unitPriceA)) {
                            unitPriceA = 0.0;
                        }
                    } catch(ArithmeticException ae){
                        unitPriceA = 0.0;
                    }
                }

                if((!textViewPriceB.getText().equals(null)) && (!textViewPoundB.getText().equals(null))
                        && (!textViewOzB.getText().equals(null)) ) {
                    getPriceB = ParseDouble(textViewPriceB.getText().toString());
                    getPoundB = (int) ParseInt(textViewPoundB.getText().toString());
                    getOzB = (int) ParseInt(textViewOzB.getText().toString());

                    try {
                        unitPriceB = (getPriceB / ((getPoundB * 16) + getOzB));
                        if (Double.isNaN(unitPriceB) || Double.isInfinite(unitPriceB)) {
                            unitPriceB = 0.0;
                        }
                    } catch (ArithmeticException ae){
                        unitPriceB = 0.0;
                    }
                }

                if((!textViewPriceC.getText().equals(null)) && (!textViewPoundC.getText().equals(null))
                        && (!textViewOzC.getText().equals(null)) ) {

                    getPriceC = ParseDouble(textViewPriceC.getText().toString());
                    getPoundC = (int) ParseInt(textViewPoundC.getText().toString());
                    getOzC = (int) ParseInt(textViewOzC.getText().toString());

                    try {
                        unitPriceC = (getPriceC / ((getPoundC * 16) + getOzC));
                        if (Double.isNaN(unitPriceC) || Double.isInfinite(unitPriceC)) {
                            unitPriceC = 0.0;
                        }
                    } catch (ArithmeticException ae){
                        unitPriceC = 0.0;
                    }
                }

                frugalBuyOutput.setText(findFrugalBuy(unitPriceA,unitPriceB,unitPriceC));

            } catch (NumberFormatException nfe) {
                Log.i(DEBUG_TAG,"Incorrect Number Format");

                textViewPriceA.setText(null);
                textViewPriceB.setText(null);
                textViewPriceC.setText(null);

                textViewOzA.setText(null);
                textViewOzB.setText(null);
                textViewOzC.setText(null);

                textViewPoundA.setText(null);
                textViewPoundB.setText(null);
                textViewPoundC.setText(null);

                frugalBuyOutput.setText("Incorrect Data Format");
            } catch (IOError ioe){
                Log.i(DEBUG_TAG,"Incorrect Number Format");

                textViewPriceA.setText(null);
                textViewPriceB.setText(null);
                textViewPriceC.setText(null);

                textViewOzA.setText(null);
                textViewOzB.setText(null);
                textViewOzC.setText(null);

                textViewPoundA.setText(null);
                textViewPoundB.setText(null);
                textViewPoundC.setText(null);

                frugalBuyOutput.setText("Incorrect Data Format");
            } catch (Exception e) {
                Log.i(DEBUG_TAG,"Incorrect Number Format for inputs provided by the user");

                textViewPriceA.setText(null);
                textViewPriceB.setText(null);
                textViewPriceC.setText(null);

                textViewOzA.setText(null);
                textViewOzB.setText(null);
                textViewOzC.setText(null);

                textViewPoundA.setText(null);
                textViewPoundB.setText(null);
                textViewPoundC.setText(null);

                frugalBuyOutput.setText("Something went wrong!!");
            }
        }
    }
}
