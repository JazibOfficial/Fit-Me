package com.jazibkhan.healthcare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalorieCounter extends Fragment{

    int height = 150;
    int weight = 50;
    int calorieCal;
    double calorieTaken = 0;
    EditText editWeight,editHeight,quantity;
    RoundCornerProgressBar calProgress;
    TextView progressText;
    Spinner spinner;
    Button button;
    List<String> food = new ArrayList<String>();
    List<Double> calories = new ArrayList<Double>();
    int counter=0;
    int i;
    double multiplier=0.52;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calorie_counter, container, false);

        editHeight = view.findViewById(R.id.editHeight);
        editWeight = view.findViewById(R.id.editWeight);
        quantity = view.findViewById(R.id.bGram);
        calProgress = view.findViewById(R.id.calProgress);
        spinner = view.findViewById(R.id.spinner);
        progressText = view.findViewById(R.id.progressText);
        button = view.findViewById(R.id.bAddFood);
        defaultFood();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, food);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor myEditor = myPreferences.edit();
        if(!myPreferences.contains("initial")) {
            myEditor.putInt("height",150);
            editWeight.setText("50");
            editHeight.setText("150");
            myEditor.putInt("weight",50);
            myEditor.putBoolean("initial", true);
            myEditor.putInt("currentday",dayOfWeek);
            myEditor.commit();
        }

        if(myPreferences.getInt("currentday",0) != dayOfWeek)
        {
            myEditor.putFloat("calorieTaken",0);
        }

        weight = myPreferences.getInt("weight",50);
        height = myPreferences.getInt("height",150);
        CountCal();
        editWeight.setText(""+weight);
        editHeight.setText(""+height);
        Log.d("WOW", "calorie *************************** "+ calorieCal );

        editHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!editHeight.getText().toString().equals("")){
                height=Integer.parseInt(editHeight.getText().toString());
                SaveChanges();
                CountCal();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        editWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!editWeight.getText().toString().equals("")){
                weight=Integer.parseInt(editWeight.getText().toString());
                SaveChanges();
                CountCal();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                multiplier=calories.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!quantity.getText().toString().equals("")) {
                    calorieTaken += multiplier * Integer.parseInt(quantity.getText().toString());
                    SaveChanges();
                    CountCal();
                }
            }
        });


        return view;
    }

    public void SaveChanges(){
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("weight",weight);
        myEditor.putInt("height",height);
        myEditor.putFloat("calorieTaken",(float) calorieTaken);
        myEditor.commit();
    }

    public void CountCal(){
        SharedPreferences myPreferences
               = PreferenceManager.getDefaultSharedPreferences(getActivity());
        calorieCal = (10*weight) + (7*height) + 200;
        calProgress.setMax(calorieCal);
        calorieTaken = myPreferences.getFloat("calorieTaken",0);
        if(calorieTaken<=calorieCal){
        calProgress.setProgress((float) calorieTaken);
        calProgress.setSecondaryProgress((float) calorieTaken);
        }

        else {
            calProgress.setProgress(calorieCal);
            calProgress.setSecondaryProgress(calorieCal);
        }
        progressText.setText(""+String.format("%.0f", calorieTaken)+"/"+calorieCal);
    }

    public void defaultFood(){
        food.add("Apple");
        calories.add(0.52);
        food.add("Banana");
        calories.add(0.95);
        food.add("Orange");
        calories.add(0.49);
        food.add("Carrot");
        calories.add(0.41);
        food.add("Tomato");
        calories.add(0.18);
        food.add("Potato");
        calories.add(0.72);
        food.add("Banana");
        calories.add(0.95);
        food.add("Chicken");
        calories.add(1.10);
        food.add("Egg");
        calories.add(1.55);
        food.add("Egg White");
        calories.add(0.52);
        food.add("Fish");
        calories.add(0.87);
        food.add("Butter");
        calories.add(7.37);
        food.add("Bread");
        calories.add(2.65);
        food.add("Milk");
        calories.add(5.30);
        food.add("Fish");
        calories.add(0.87);
        counter=15;
    }
}


