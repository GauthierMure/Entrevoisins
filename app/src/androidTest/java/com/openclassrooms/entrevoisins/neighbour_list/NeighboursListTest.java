
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    private int position = 0;

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);


    @Before
    public void setUp() {
        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withIndex(withId(R.id.list_neighbours),0))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withIndex(withId(R.id.list_neighbours),0)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withIndex(withId(R.id.list_neighbours),0))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withIndex(withId(R.id.list_neighbours),0)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     *test if neighbour info are displayed
     */
    @Test
    public void NeighbourInfo_NeighbourInfoDisplayed(){
        openNeighbourInfoAt(position);

        onView(withId(R.id.nameTv)).check(matches(withText(mNeighbours.get(position).getName())));
        onView(withId(R.id.locationTv)).check(matches(withText(mNeighbours.get(position).getAddress())));
        onView(withId(R.id.phoneTv)).check(matches(withText(mNeighbours.get(position).getPhoneNumber())));
        onView(withId(R.id.mailTv)).check(matches(withText(mNeighbours.get(position).getURL())));
        onView(withId(R.id.aboutMeTv)).check(matches(withText(mNeighbours.get(position).getAboutMe())));
    }

    /**
     * check if we can add neighbour to favorite listview
     */
    @Test
    public void myNeighbourList_addFavoriteNeighbour(){
        makeFavorite(position);

        onView(withIndex(withId(R.id.list_neighbours),1)).check(withItemCount(1));
    }

    @Test
    public void myNeighbourList_removeFavoriteNeighbour(){
        onView(withIndex(withId(R.id.list_neighbours),1)).check(withItemCount(1));

        makeFavorite(position);

        onView(withIndex(withId(R.id.list_neighbours),1)).check(withItemCount(0));
    }

    private void openNeighbourInfoAt(int p){
        onView(withIndex(withId(R.id.list_neighbours),0)).perform(RecyclerViewActions.actionOnItemAtPosition(p, click()));
    }

    private void makeFavorite(int p){
        openNeighbourInfoAt(p);
        onView(withId(R.id.favoriteFBtn)).perform(click());
        onView(withId(R.id.returnIBtn)).perform(click());
    }
}