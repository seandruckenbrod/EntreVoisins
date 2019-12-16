
package com.openclassrooms.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.utils.DeleteViewAction;


import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Instrumented test
 */

@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int neighbourIndex = 2;
    private NeighbourApiService mService;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    // 1. at launch the list contains at least one neighbour
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {

        // TODO : Error: "android.support.test.espresso.AmbiguousViewMatcherException"
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }


    // 2. to click on the delete button there is a neighbour less
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {

        // TODO : Error: "android.support.test.espresso.AmbiguousViewMatcherException"

        // Given : The initial size of the list is 12
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When performing a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : The number of elements is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));

    }


    // 3. Neighbour detail activity page displayed on click of a neighbour/favorite list
    @Test
    public void neighbour_details_activity_page_isDisplayed() {
// click on first displayed neighbour on the list
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(1, click()));
// check if detail layout is shown
        onView(withId(R.id.detailGlobalLayout))
                .check(matches(isDisplayed()));
    }


    // 4.  Neighbour name is displayed on his/her detail activity page
    @Test
    public void neighbour_details_activity_name_isDisplayed() {

// click on second displayed neighbour on the list
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));
// check if detail layout is shown
        onView(withId(R.id.detailGlobalLayout))
                .check(matches(isDisplayed()));

        onView(withId(R.id.Neighbour_name_txt)).check(matches((withText("Caroline"))));
    }


    // 5.Favorites tab only shows favorite neighbors

    //indexing of views of the viewpager which contains the list of favorites and that of favorites
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {

            int currentIndex = 0;

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);

            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex ++== index;
            }
        };
    }

        @Test
    public void favorites_tab_only_shows_favorite_neighbors() {


        // click on second displayed neighbour on the list
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(neighbourIndex, click()));

        // click on the favorite icon
        onView(allOf(withId(R.id.activity_neighbour_details_infos), isDisplayed()));
        onView(withId(R.id.add_favorite_button_btn)).perform(click());

        //back to the list of neighbour
        pressBack();

        //toggle on favorite tab
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()));
        onView(withId(R.id.container)).perform(scrollRight());

        //verification of the number of favorites passing by indexing of views
        onView(withIndex(withId(R.id.list_neighbours), 1))
                .check(withItemCount(1));

    }


  }