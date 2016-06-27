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
    int quantity = 1;
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
        String priceMessage = calculatePrice() + "\nThank You!";
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order based on the current quantity and price per unit.
     * TODO: Actually, it seems to be just the one method which doens't call currency instance.
     * */
    private double calculatePrice() {
        return quantity * pricePerUnit;
    }

    /**
     * The following methods increment or decrement the number of coffees.
     * Decrement will not allow the order quantity to go below 0.
     */
    public void increment(View view) {
        quantity ++;
        displayQuantity(quantity);
        displayPrice(calculatePrice());
    }

    public void decrement(View view) {
        if (quantity > 1){
            quantity --;
        }
        displayQuantity(quantity);
        displayPrice(calculatePrice());
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
    private void displayPrice(double number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
