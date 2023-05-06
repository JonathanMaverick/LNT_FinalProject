package com.example.finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    private TextView counterTextView;
    private Button plusButton, minusButton, resetButton;

    private int counter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        counterTextView = view.findViewById(R.id.tv_number);
        plusButton = view.findViewById(R.id.btn_plus);
        minusButton = view.findViewById(R.id.btn_minus);
        resetButton = view.findViewById(R.id.btn_reset);

        // Isi Counter untuk value
        counter = 0;
        counterTextView.setText(String.valueOf(counter));

        // Set button untuk plus, minus, dan reset
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                counterTextView.setText(String.valueOf(counter));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                counterTextView.setText(String.valueOf(counter));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                counterTextView.setText(String.valueOf(counter));
            }
        });

        return view;
    }
}






