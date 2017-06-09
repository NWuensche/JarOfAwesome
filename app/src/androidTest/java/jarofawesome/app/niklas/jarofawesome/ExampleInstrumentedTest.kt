package jarofawesome.app.niklas.jarofawesome

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumentation test, which will execute on an Android device.

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleInstrumentedTest : SuperEspresso() {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("jarofawesome.app.niklas.jarofawesome", appContext.packageName)
    }

    @Test
    fun testSwitchToAddActivity() {
        onView(withId(R.id.hello)).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.addText)).check(matches(isDisplayed()))
    }


}
