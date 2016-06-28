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
    int pricePerUnit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        String priceMessage = calculatePrice() + "\nThank You!";
//        displayMessage(priceMessage);
        createOrderSummary();
    }

    /**
     * Calculates the price of the order based on the current quantity and price per unit.
     * TODO: Actually, it seems to be just the one method which doens't call currency instance.
     */
    private String calculatePrice() {
        return "" + (quantity * pricePerUnit);
        }

    /**
     * The following methods increment or decrement the number of coffees.
     * Decrement will not allow the order quantity to go below 0.
     */
    private void increment(View view) {
        quantity++;
        displayQuantity(quantity);
        displayMessage(calculatePrice());
    }

    private void decrement(View view) {
        if (quantity > 1) {
            quantity--;
        }
        displayQuantity(quantity);
        displayMessage(calculatePrice());
    }

    /**
     * Create summary of order and return it as text based summary.
     */

    private void createOrderSummary() {
        String name = "Kaptain Kunal";
        displayMessage("Name: " + name +
                "\nQuantity: " + quantity +
                "\nTotal: " + calculatePrice());
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
        TextView orderSummaryTextView = (TextView) findViewById(
                R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
        orderSummaryTextView.setLineSpacing(2, 2);
    }
}
