package com.example.tipcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener, test {

    private double billAmount;
    private double percent;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private EditText amountEditText;
    private SeekBar percentSeekBar;

    private static  NumberFormat currencyFormat;
    private static  NumberFormat percentFormat;
    private Toolbar toolbar;
    private MenuInflater MenuInflaterinflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init()
    {
        billAmount = 0.0;
        percent = 0.15;

        amountTextView = findViewById(R.id.amountTextView);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        amountEditText = findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(this);

        percentSeekBar = findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(this);

        currencyFormat = NumberFormat.getCurrencyInstance();
        percentFormat = NumberFormat.getPercentInstance();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        setTitle(" My new title");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.item1:
                Toast toast =  Toast.makeText(this,
                        "This a toast message",
                        Toast.LENGTH_LONG);
                toast.show();
                return true;
           case R.id.item2:
                Toast toast1 = Toast.makeText(this,
                        "This a toast message 2",
                        Toast.LENGTH_LONG);
                toast1.show();
                return true;
            case R.id.item3:
                Toast toast3 = Toast.makeText(this,
                        "This a toast message 3",
                        Toast.LENGTH_LONG);
                toast3.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(amountTextView.getWindowToken(), 0);
    }

    private void calculate()
    {
        try{
            billAmount = Integer.parseInt(amountEditText.getText().toString());
        }
        catch (NumberFormatException e)
        {
            billAmount = 0.0;
        }

        double tip = billAmount*percent;
        double total = billAmount + tip;


        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {
//        amountTextView.setText(amountEditText.getText().toString());
        amountTextView.setText(currencyFormat.format(billAmount));

        calculate();

    }

    @Override
    public void afterTextChanged(Editable editable)
    {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
    {
        percent = progress / 100.0;
//        percentTextView.setText(String.valueOf(percent));
        percentTextView.setText(percentFormat.format(percent));

        calculate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
        hideKeyboard();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {

    }
}