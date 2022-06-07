package com.openclassrooms.entrevoisins.neighbour_list;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNull.notNullValue;

import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourInfoActivity;
import com.openclassrooms.entrevoisins.utils.customActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class NeighbourInfoTest{

    private int mPositon = 0;
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    private NeighbourInfoActivity mActivity;

    @Rule
    public customActivityTestRule mActivityRule = new customActivityTestRule(NeighbourInfoActivity.class);

    @Before
    public void setUp(){
        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
        mActivityRule.setPosition(mPositon);
        mActivity =(NeighbourInfoActivity) mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void NeighbourInfo_isDisplayed(){

    }
}
