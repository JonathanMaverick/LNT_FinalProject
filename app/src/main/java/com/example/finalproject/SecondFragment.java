package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SecondFragment extends Fragment {

    private Spinner spinner;
    private EditText etInput;
    private TextView tvResult;
    private Button btnCalculate;

    public SecondFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        spinner = view.findViewById(R.id.spinner);
        etInput = view.findViewById(R.id.et_input);
        tvResult = view.findViewById(R.id.tv_result);
        btnCalculate = view.findViewById(R.id.btn_calculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateArea();
            }
        });

        return view;
    }

    private void calculateArea() {
        int selectedItemPosition = spinner.getSelectedItemPosition();
        double input = Double.parseDouble(etInput.getText().toString());
        double result = 0;
        double jarijari = 0;

        switch (selectedItemPosition) {
            case 0: // Persegi
                result = input * input;
                break;
            case 1: // Segitiga
                result = 0.5 * input * (input / 2);
                break;
            case 2: // Lingkaran
                jarijari = input / 2;
                result = 3.14 * (jarijari * jarijari);
                break;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        tvResult.setText("Result: " + df.format(result));
    }
}
