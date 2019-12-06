package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class NeighbourActivityTest {
    private static int neighbourIndex = 2;
    private NeighbourApiService mService;
    private List<Neighbour> instantTestList;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Before
    //  Generate an instant neighbour list 'on the fly' thtough API
    public void linkToAPI() {
        mService = DI.getNewInstanceApiService();
        instantTestList = new ArrayList<>();
        for (Neighbour n : mService.getNeighbours()) {
            if (n.isFavorite())
                instantTestList.add(n);
        }
    }

    // 1. Neighbour detail activity page displayed on click of a neighbour/favorite list
    @Test
    public void neighbour_details_activity_page_isDisplayed() {
// click on first displayed neighbour on the list
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(1, click()));
// check if detail layout is shown
        onView(withId(R.id.detailGlobalLayout))
                .check(matches(isDisplayed()));
    }

    // 2.  Neighbour name is displayed on his/her detail activity page
    @Test
    public void neighbour_details_activity_name_isDisplayed(){

// click on second displayed neighbour on the list
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(neighbourIndex, click()));

//  check if neighbour flield matches with the second name stored in the list of neighbours
        onView(withId(R.id.Neighbour_name_txt))
                .check(matches(withText(mService.getNeighbours().get(neighbourIndex).getName())));
    }


}