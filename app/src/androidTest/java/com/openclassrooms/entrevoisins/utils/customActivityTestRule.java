package com.openclassrooms.entrevoisins.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

public class customActivityTestRule extends ActivityTestRule {

    private int mPosition = 0;

    public customActivityTestRule(Class activityClass) {
        super(activityClass);
    }

    @Override
    public Intent getActivityIntent(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.putExtra("position",(long) mPosition);
        return intent;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }
}
