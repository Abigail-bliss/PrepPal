package com.ui.preppal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.ui.preppal.CalendarUtils.daysInMonthArray;
import static com.ui.preppal.CalendarUtils.monthYearFromDate;

import com.ui.preppal.lunch.LunchFragment;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    Button callLunch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        startActivity(new Intent(this, WeekViewActivity.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch_fragment);
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();
//        callLunch = findViewById(R.id.lunch);
//        callLunch.setOnClickListener(this);
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    public void weeklyAction(View view)
    {
        startActivity(new Intent(this, WeekViewActivity.class));
    }

//    @Override
    public void goToLunchPage(View view) {
//        if (view.getId()==R.id.lunch) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.weekly, new LunchFragment()).commit();
            startActivity(new Intent(this, LunchActivity.class));
//        }
    }
}








