package com.example.android.toojusttoojava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * These global variables store the quantity for an order and price per unit.
     * Also added variables for the price of whipped cream and chocolate add-ons,
     * as well as booleans to track their presence.
     */
    int quantity = 1;
    final int pricePerUnit = 5;
    final int whippedCream = 1;
    final int chocolatePrice = 2;
    boolean cream = false;
    boolean chocolate = false;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String plusCream = "No";
        String plusChocolate = "No";
        CheckBox whippedCheckBox = (CheckBox) findViewById(R.id.checkWhipped);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkChocolate);
        cream = whippedCheckBox.isChecked();
        chocolate =  chocolateCheckBox.isChecked();
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:"));
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, ("Just Java order"));
        mailIntent.putExtra(Intent.EXTRA_TEXT, ("Name: " + name +
                "\nQuantity: " + quantity +
                "\nWhipped cream: " + plusCream +
                "\nChocolate: " + plusChocolate +
                "\nTotal: " + calculatePrice()));
        if (mailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(mailIntent, "Send Email"));
        }
        createOrderSummary();
    }

    /**
     * Calculates the price of the order based on the current quantity and price per unit.
     * TODO: Actually, it seems to be just the one method which doesn't call currency instance.
     */
    private String calculatePrice() {
        CheckBox whippedCheckBox = (CheckBox) findViewById(R.id.checkWhipped);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkChocolate);
        cream = whippedCheckBox.isChecked();
        chocolate =  chocolateCheckBox.isChecked();
        if (!cream && !chocolate) {
            return "" + (quantity * pricePerUnit);
        }
        if (cream && !chocolate) {
            return "" + ((quantity * pricePerUnit) + (whippedCream * quantity));
        }
        if (!cream && chocolate) {
            return "" + ((quantity * pricePerUnit) + (chocolatePrice * quantity));
        }
        return "" + ((quantity * pricePerUnit) + (whippedCream * quantity)
                + (chocolatePrice * quantity));
    }

        /**
         * The following methods increment or decrement the number of coffees.
         * Decrement will not allow the order quantity to go below 0.
         */

    public void increment(View view) {
        if (quantity == 99){
            Toast.makeText(this, "You cannot order more than 99 coffees!", Toast.LENGTH_SHORT).show();
            displayQuantity(quantity);
            createOrderSummary();
            return;
        }
        quantity ++;
        displayQuantity(quantity);
        createOrderSummary();
    }

    public void decrement(View view) {
        if (quantity == 0) {
            Toast.makeText(this, "You cannot sell us the coffee!!", Toast.LENGTH_SHORT).show();
            displayQuantity(quantity);
            createOrderSummary();
            return;
        }
        quantity--;
        displayQuantity(quantity);
        createOrderSummary();
    }

    /**
     * Create summary of order and return it as text based summary.
     */

    private void createOrderSummary() {
        TextView nameTextView = (TextView) findViewById(R.id.name_field); /** Needs to be tweaked */
        name = nameTextView.getText().toString(); /** to make sure it updates constantly. */
        String plusCream = "No";
        String plusChocolate = "No";
        CheckBox whippedCheckBox = (CheckBox) findViewById(R.id.checkWhipped);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkChocolate);
        cream = whippedCheckBox.isChecked();
        chocolate =  chocolateCheckBox.isChecked();
        if (cream) {
            plusCream = "Yes";
        }
        if (chocolate) {
            plusChocolate = "Yes";
        }
        displayMessage("Name: " + name +
                "\n" + getString(R.string.quantity) + quantity +
                "\n" + getString(R.string.cream) + plusCream +
                "\n" + getString(R.string.chocolate) + plusChocolate +
                "\n" + getString(R.string.total) + calculatePrice());
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void addCream(View view) {
        createOrderSummary();
    }

    public void addChocolate(View view) {
        createOrderSummary();
    }

    public void addName(View view){
        createOrderSummary();
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
