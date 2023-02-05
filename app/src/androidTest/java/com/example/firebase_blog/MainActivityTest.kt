package com.example.firebase_blog

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.firebase_blog.ui.email_authentication.EmailLoginActivity
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testButtonClickNavigatesToEmailLoginActivity() {
        onView(withId(R.id.btnEmailAuth)).perform(click())
    }

    @Test
    fun testEmailLoginActivityIsLaunchedAfterButtonClick() {
        ActivityScenario.launch(EmailLoginActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertThat(activity, instanceOf(EmailLoginActivity::class.java))
            }
        }
    }
}