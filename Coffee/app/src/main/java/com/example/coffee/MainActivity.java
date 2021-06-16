package com.example.coffee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private static int SPALASH_TIME_OUT = 4000;
    int numberOfCoffees = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
//                startActivity(homeIntent);
//                finish();
//            }
//        },SPALASH_TIME_OUT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void submitOrder(View view){
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name  = nameField.getText().toString();
        Log.v("MainActivity", "Name: "+ name);
        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.notify_me_checkbox);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped cream: "+ hasWhippedCream);
        CheckBox Chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean haschocolate = Chocolate.isChecked();
        Log.v("MainActivity", "Has Chocolate: "+ haschocolate);
        int price = calculatePrice();
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, haschocolate);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffie order of " + name);
        intent.putExtra(Intent.EXTRA_TEXT, "Coffie order of " + priceMessage);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
//        Intent sendIntent = new Intent(); sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello bhaiya!!\nThis text is from my APP.");
//        sendIntent.setType("text/plain");
//        startActivity(sendIntent);

        displayMessage(priceMessage);

    }

    public void increment(View view) {

        numberOfCoffees = numberOfCoffees+1;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * 5);
    }

    public void decrement(View view) {

        numberOfCoffees = numberOfCoffees -1;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * 5);
    }

    private void display(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+ number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice() {
        int price = numberOfCoffees * 5;

        return price;


    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addchocolate){
        String priceMessage = "Name : " + name;
        priceMessage +=  "\nQuantity : " + numberOfCoffees;
        priceMessage +=  "\nAdd WhippedCream : " + addWhippedCream;
        priceMessage +=  "\nAdd Chocolate : " + addchocolate;
        priceMessage += "\nTotal $ "+ price;
        priceMessage += "\nThank You!!";
        return priceMessage;
    }

}