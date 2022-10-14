package com.ui.preppal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import java.time.LocalDate;
import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity
{


    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;
    public int dayNum = 1;
    private String time;

    String[] meals = {"Breakfast", "Lunch", "Dinner"};
    String[] days = {"1", "2", "3", "4", "5", "6", "7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) //creates spinner
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();

        //meal spinner
        Spinner spino = findViewById(R.id.eventTimeSpinner);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meals);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(ad);
        spino.setOnItemSelectedListener(new mealsSpinnerClass());

        // numDaysSpinner
        Spinner spinNum = (Spinner) findViewById(R.id.numDaysSpinner);
        ArrayAdapter<String> numD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        numD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinNum.setAdapter(numD);
        spinNum.setOnItemSelectedListener(new NumDaysSpinnerClass());


        time = "Breakfast";
        eventDateTV.setText("Start Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));

        if(WeekViewActivity.selected == 1){
            TextView RecipeView = (TextView)findViewById(R.id.RecipeView);
            RecipeView.setText(WeekViewActivity.recipe);

            EditText editText = (EditText)findViewById(R.id.eventNameET);
            editText.setText(WeekViewActivity.nameOfRec, TextView.BufferType.EDITABLE);

            spino.setSelection(WeekViewActivity.mealTypes);
            time = meals[WeekViewActivity.mealTypes];
        }
        //eventTimeTV.setText("Meal: " + time);
    }



    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        //eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view)                  //sends to calendar
    {
        LocalDate current =  CalendarUtils.selectedDate;        //selects date that is currently selected on calendar
        for(int i = 0; i < dayNum; i++) {                           //we set dayNum from spinner (dayNumSpinner class), converting from string to int
            String eventName = eventNameET.getText().toString();    //setting strong from editText button to be title of recipe
            Event newEvent = new Event(eventName, current, time);       //what we use to set breakfast, lunch, dinner
            Event.eventsList.add(newEvent);
            current = current.plusDays(1);
        }//
        finish();
    }

    public void backEventAction(View view)                      //if you click cancel, sets view back to main activity week view
    {
        setContentView(R.layout.activity_week_view);
        finish();

    }

    class mealsSpinnerClass implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            // make toastof name of course
            // which is selected in spinner
            Toast.makeText(view.getContext(), meals[i], Toast.LENGTH_LONG).show();
            time = meals[i];                                                                //sets spinner to be in meals array

        }


        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }

    class NumDaysSpinnerClass implements AdapterView.OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(view.getContext(), days[i], Toast.LENGTH_LONG).show();
            dayNum = Integer.parseInt(days[i]);

        }

        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


}