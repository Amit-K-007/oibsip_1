package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("Kilometer","Meter","Centimeter","Millimeter","Micrometer","Nanometer","Mile","Yard","Foot","Inch"));
    private Spinner spinner;
    private Spinner spinner2;
    private TextView textView2,textView3,textView4,textView5;
    private String buttonText="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.spinner_list,arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        spinner.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setSelection(1);

        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);

        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }
    public void onClick(View view) {
        Button button = (Button) view;
        String str = button.getText().toString();
        if (str.equals("AC")) {
            textView2.setText("0");
            textView4.setText("0");
            buttonText = "";
        } else if (str.equals("⌫")) {
            if (buttonText != "0" && buttonText.length() == 1) {
                textView2.setText("0");
                textView4.setText("0");
                buttonText = "";
            } else if (buttonText.length() > 1) {
                Log.e("my", "onClick: " + (buttonText.length() - 1));
                buttonText = buttonText.substring(0, buttonText.length() - 1);
                textView2.setText(buttonText);
                convertMeter();
            }
        } else if ((str.equals("⬆⬇"))) {
            int position = spinner.getSelectedItemPosition();
            spinner.setSelection(spinner2.getSelectedItemPosition());
            spinner2.setSelection(position);
        } else if (str.equals(".") && buttonText.length()<=6) {
            if (!buttonText.contains(".")) {
                if (buttonText.equals("")) {
                    textView2.setText("0.");
                    buttonText = "0.";
                } else {
                    buttonText = buttonText + str;
                    textView2.setText(buttonText);
                }
            }
        } else if (buttonText.equals("") && str.equals("0")) {
        } else {
            if(buttonText.length()<=7) {

                buttonText = buttonText + str;
                textView2.setText(buttonText);
                convertMeter();
                Log.d("my", buttonText.length()+"onClick: "+buttonText);
            }
        }
    }
    void convertMeter(){
        int position = spinner.getSelectedItemPosition();
        double value = Double.parseDouble(buttonText);
        switch (arrayList.get(position)) {
            case "Kilometer":
                value = value * 1000;
                break;
            case "Centimeter":
                value = value / 100;
                break;
            case "Millimeter":
                value = value / 1000;
                break;
            case "Micrometer":
                value = value / 1000000;
                break;
            case "Nanometer":
                value = value / 1000000000;
                break;
            case "Mile":
                value = value * 1609.344;
                break;
            case "Yard":
                value = value * 0.9144;
                break;
            case "Foot":
                value = value * 0.3048;
                break;
            case "Inch":
                value = value * 0.0254;
                break;
        }
        textView4.setText(finalConvert(value));
    }
    String finalConvert(Double value){
        int position = spinner2.getSelectedItemPosition();
        switch (arrayList.get(position)) {
            case "Kilometer":
                value = value / 1000;
                break;
            case "Centimeter":
                value = value * 100;
                break;
            case "Millimeter":
                value = value * 1000;
                break;
            case "Micrometer":
                value = value * 1000000;
                break;
            case "Nanometer":
                value = value * 1000000000;
                break;
            case "Mile":
                value = value / 1609;
                break;
            case "Yard":
                value = value / 0.9144;
                break;
            case "Foot":
                value = value / 0.3048;
                break;
            case "Inch":
                value = value / 0.0254;
                break;
        }
        return String.valueOf(value);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(!buttonText.equals("")){
            convertMeter();
        }
        textView3.setText(arrayList.get(spinner.getSelectedItemPosition()));
        textView5.setText(arrayList.get(spinner2.getSelectedItemPosition()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}