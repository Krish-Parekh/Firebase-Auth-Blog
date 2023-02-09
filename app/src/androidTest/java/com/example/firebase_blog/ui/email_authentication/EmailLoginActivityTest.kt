package com.example.firebase_blog.ui.email_authentication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.firebase_blog.R
import com.example.firebase_blog.ui.email_authentication.viewmodel.EmailAuthViewModel
import com.example.firebase_blog.util.Resource
import com.example.firebase_blog.util.Status
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@LargeTest
@RunWith(AndroidJUnit4::class)
class EmailLoginActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(EmailLoginActivity::class.java)

    @Test
    fun testEditTextForCredentials() {
        onView(withId(R.id.etEmail)).perform(typeText("krish@gmail.com"))
        onView(withId(R.id.etPassword)).perform(typeText("Krish123@#$"))
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
    }

    @Test
    fun testForgotPasswordNavigateToForgotPassActivity() {
        onView(withId(R.id.ctaForgotPassword)).perform(click())
    }

    @Test
    fun testForgotPasswordActivityIsLaunchedAfterButtonClick() {
        ActivityScenario.launch(EmailForgotPasswordActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                ViewMatchers.assertThat(
                    activity,
                    IsInstanceOf.instanceOf(EmailForgotPasswordActivity::class.java)
                )
            }
        }
    }
}