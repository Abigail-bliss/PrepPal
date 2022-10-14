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

public class LunchActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch_fragment);
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

    public void goToBreakfastPage(View view) {
        startActivity(new Intent(this, WeekViewActivity.class));
    }

    public void goToDinnerPage(View view) {
        startActivity(new Intent(this, DinnerActivity.class));
    }

    public void hummusBowl(View view){                  //global variables
        WeekViewActivity.nameOfRec = "Hummus Bowl";
        WeekViewActivity.mealTypes = 1;
        WeekViewActivity.selected = 1;
        WeekViewActivity.recipe = ("Ingredients:\n 1/3 cup hummus\n8 cucumber slices\n1 handful red onion slices\n1 handful cherry tomatoe slices\n1 handful Kalamata olives\n2 tablespoons feta cheese\n1 handful baby greens\n1/2 cup cooked rice\n1 pita bread\n\n\n Directions:\nPlace greens and rice in the bowl, if using (try packaged pre-cooked rice for a quick shortcut). If using rice, season it with salt and a drizzle of olive oil.\n\nTop with hummus, sliced cucumber, sliced red onions, sliced tomatoes, olives and feta cheese. Eat with pita wedges, using the hummus as a dip/dressing for the veggies.\n\n\n");
        startActivity(new Intent(this, EventEditActivity.class));
    }

    public void stirFry(View view){
        WeekViewActivity.nameOfRec = "Stir Fry";
        WeekViewActivity.mealTypes = 1;
        WeekViewActivity.selected = 1;
        WeekViewActivity.recipe = ("Ingredients:\n1 tablespoon olive oil\n1 red bell pepper sliced\n1 yellow bell pepper sliced\n1 cup sugar snap peas\n1 cup carrots sliced\n1 cup mushrooms sliced\n2 cups broccoli\n1 cup baby corn\n1/2 cup water chestnuts\n3/4 cup soy sauce\n3 garlic cloves minced\n3 Tablespoons brown sugar\1 teaspoon sesame oil\n1/2 cup chicken broth\n1 tablespoon cornstarch\ngreen onions and sesame seeds for garnish\n\n\nDirections:\nIn a wok or large skillet add 1 Tablespoon olive oil over medium high heat. Add bell pepper, peas, carrots, mushrooms, broccoli, baby corn, and water chestnuts. Sauté 2-3 minutes until veggies are almost tender.\n\nIn a small whisk together soy sauce, garlic, brown sugar, sesame oil, chicken broth, and cornstarch.\n\nPour over veggies and cook until the sauce has thickened. Garnish with chopped green onions and sesame seeds if desired\n\n\n");
        startActivity(new Intent(this, EventEditActivity.class));
    }
}