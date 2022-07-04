/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ToastTest {
    @Rule
    public ActivityScenarioRule<GardenActivity> activityRule =
            new ActivityScenarioRule<>(GardenActivity.class);
    private View decorView;
    @Before
    public void setUp() {
        activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<GardenActivity>() {
            @Override
            public void perform(GardenActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }
    //Test if the Toast Message is Displayed
    @Test
    public void testValidToast() {
        // Given that no Plants are added to the user's garden

        // When the "Add Plant" button is clicked(Yellow Button)
        onView(withId(R.id.add_plant)).perform(click());
        //Verify Toast is Displayed After clicking the Plant List Fragment
        onView(withText("Welcome For The Visit"))
                .inRoot(withDecorView(Matchers.not(decorView)))// Here we use decorView
                .check(matches(isDisplayed()));
    }
    //Test if the Toast Message is not Displayed
    @Test
    public void testToastNotDisplayed() {
        // Given that no Plants are added to the user's garden

        // When the "Add Plant" button is clicked(Yellow Button)
        onView(withId(R.id.add_plant)).perform(click());
        //Verify Toast is Displayed After clicking the Plant List Fragment
        onView(withText("Your Toast"))
                .inRoot(withDecorView(Matchers.not(decorView)))// Here we use decorView
                .check(matches(not(isDisplayed())));
    }
}
