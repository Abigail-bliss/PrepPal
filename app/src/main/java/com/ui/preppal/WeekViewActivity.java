package com.ui.preppal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.ui.preppal.CalendarUtils.daysInWeekArray;
import static com.ui.preppal.CalendarUtils.monthYearFromDate;

import com.ui.preppal.lunch.LunchFragment;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private static ListView eventListView;
    public static String nameOfRec = "";
    public static String recipe = "";
    public static int selected = 0; // 1 if a recipe was selected
    public static int mealTypes; // 0 = breakfast, 1 = lunch, 2 = dinner

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        setWeekView();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }

    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdpater();
    }


    public void previousWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdpater();
    }

    private void setEventAdpater()
    {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    public void newEventAction(View view)
    {
        startActivity(new Intent(this, EventEditActivity.class));
    }

    public void goToLunchPage(View view) {
        startActivity(new Intent(this, LunchActivity.class));
    }

    public void goToDinnerPage(View view) {
        startActivity(new Intent(this, DinnerActivity.class));
    }

    public void parfait(View view){
        nameOfRec = "Parfait";
        mealTypes = 0;
        selected = 1;
        recipe = ("Ingredients:\n" + "3 cups vanilla nonfat yogurt\n" +"1 cup fresh or defrosted frozen strawberries in juice\n" + "1 pint fresh blackberries, raspberries or blueberries\n"+ "1 cup good quality granola" + "\n\n\nDirections:\n" +
                "Layer 1/3 cup vanilla yogurt into the bottom each of 4 tall glasses. \nCombine defrosted strawberries and juice with fresh berries. Alternate layers of fruit and granola with yogurt until glasses are filled to the top. \nServe parfaits immediately to keep granola crunchy.\n\n\n");
        startActivity(new Intent(this, EventEditActivity.class));

    }

    public void omelette(View view){
        nameOfRec = "Omelette";
        mealTypes = 0;
        selected = 1;
        recipe = ("Ingredients:\n" + "2 large eggs\n" +"1 tablespoon unsalted butter\n" + "2 tablespoons grated cheese, any kind\n"+ "3 to 4 cherry tomatoes, cut in half and sprinkled lightly with salt\n" + "2 tablespoons chopped basil, parsley, or herb of your choice" + "\n\n\nDirections:\n" +
                "In a bowl, beat the eggs with a fork.\n" + "In an 8-inch nonstick skillet over medium-low heat, melt the butter.\n\nAdd the eggs to the skillet and cook without stirring until the edges begin to set. With a silicone spatula, push the edges toward the center of the pan and tilt the pan so the uncooked eggs move to the edge.\n\nRepeat until the eggs are somewhat set but still a little soft in the center, about 6 minutes.\n\nPlace the cheese, tomatoes, and herbs in a line down the center of the omelette and cook for about 1 minute longer, or until the eggs are mostly set but still a little soft in the center.\n\n Slide the spatula around one side of the omelette at the edge to loosen it. Slip it under the eggs, and use it to carefully fold the omelette in half. Slide the spatula under the folded omelette to loosen it from the pan. Tilt the pan over a plate and use the spatula to nudge it onto the plate. Voila!\n\n\n");
        startActivity(new Intent(this, EventEditActivity.class));
    }






}


