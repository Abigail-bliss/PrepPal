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

public class DinnerActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
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

    public void goToLunchPage(View view) {
        startActivity(new Intent(this, LunchActivity.class));
    }

    public void coconutcurry(View view){
        WeekViewActivity.nameOfRec = "Coconut Curry";
        WeekViewActivity.mealTypes = 2;
        WeekViewActivity.selected = 1;
        WeekViewActivity.recipe = ("Ingredients:\n1 Tbs coconut oil\n1 small onion\n4 cloves garlic\n1Tbsp fresh grated ginger\n1/2cup broccoli florets\n1/2 diced carrots\n1/4 cup dieced tomato\n1/3 cup snow peas\n1 Tbsp Curry Powder\n1 pinch cayenne\n2 14oz cans of coconut milk\n1 cup veggie broth\n\n\nDirections:\nIf serving with coconut quinoa, begin by washing thoroughly in a fine mesh strainer. Add to a medium saucepan over medium heat and toast for 3 minutes. Add light coconut milk and 1/2 cup water (amount as original recipe is written // adjust if altering batch size). Bring to a boil, then reduce heat to simmer, cover and cook for 15 minutes or until the quinoa is light, fluffy and the liquid is absorbed. Set aside until serving.\n\nIn the meantime, heat a large saucepan or pot to medium heat and add coconut oil. Add the onion, garlic, ginger, carrot, broccoli and a pinch each salt and pepper and stir. Cook, stirring frequently, until softened – about 5 minutes.\n\nAdd curry powder, cayenne (or chili pepper), veggie stock, coconut milk, another healthy pinch of salt and stir. Bring to a simmer then reduce heat slightly and continue cooking for 10-15 minutes.\n\nAdd the snow peas and tomatoes in the last 5 minutes so they don’t overcook.\n\nTaste and adjust seasonings as needed. I added another pinch or two of salt.\n\nServe over coconut quinoa (see other options below in notes) and garnish with fresh lemon juice and herbs.\n\n\n");
        startActivity(new Intent(this, EventEditActivity.class));
    }

    public void falafel(View view){
        WeekViewActivity.nameOfRec = "Falafel Salad";
        WeekViewActivity.mealTypes = 2;
        WeekViewActivity.selected = 1;
        WeekViewActivity.recipe = ("Ingredients:\n1 can chickpeas\n1 tablespoon minced garlic\n1 medium onion, finely chopped\n2 tablespoons finely chopped parsley\n1 teaspoon ground coriander\n3/4 teaspoon sald\nBlack Peper\n2 to 3 tablespoons flour\n1 1/2 olive oil\n\n\nDirections:\nCombine chickpeas, garlic, onion, parsley, coriander, cumin, salt, and pepper (to taste) in a medium bowl. Add 2 tablespoons of flour and combine well.\n\nMash chickpeas, making sure ingredients are thoroughly mixed together and mixture binds easily when shaped into a ball. (Alternatively, combine ingredients in a food processor. Pulse, then process, scraping down bowl occasionally with a silicone spatula, until mixture is mostly smooth, about 1 minute.) Add an extra tablespoon of flour if mixture is too sticky. The result should be a thick paste.\n\nForm mixture into pingpong-sized balls. Slightly flatten.\n\nAdd oil to a large skillet and heat over medium-high until oil shimmers. Shallow-fry falafel in batches to avoid crowding pan, 2 to 5 minutes per batch, flipping when browned on one side and adding more oil, if needed. Remove from skillet and drain on paper towels. Serve falafel by itself, or with warm pita bread with veggies, or tahini sauce.\n\n\n");
        startActivity(new Intent(this, EventEditActivity.class));
    }

}