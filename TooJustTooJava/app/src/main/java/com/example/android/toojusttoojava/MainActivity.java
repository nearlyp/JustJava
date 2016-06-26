package com.example.android.toojusttoojava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * These global variables store the quantity for an order and price per unit.
     */
    int quantity = 0;
    double pricePerUnit = 2.50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = "Your Price: $" + calculatePrice(quantity);
	    priceMessage = priceMessage + "\n Thank You!";
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order based on the current quantity and price per unit.
     * TODO: Currently returns a double, will need to be adjusted later.
     * */
    private double calculatePrice(int quantity) {
        return quantity * pricePerUnit;
    }

    /**
     * The following methods increment or decrement the number of coffees.
     * Decrement will not allow the order quantity to go below 0.
     */
    public void increment(View view) {
        quantity ++;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity > 0){
            quantity --;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the price when passed to string.
     */
    private void displayMessage(String message) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.price_text_view);
        quantityTextView.setText(message);
    }
    /**
     * This method displays the given price on the screen. Currently unused.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
