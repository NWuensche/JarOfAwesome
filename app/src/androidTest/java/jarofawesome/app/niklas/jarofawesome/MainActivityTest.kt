package jarofawesome.app.niklas.jarofawesome

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not
import org.jetbrains.anko.db.*
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.uiautomator.UiDevice




/**
 * Instrumentation test, which will execute on an Android device.

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class MainActivityTest: SuperEspresso() {

    @Before
    fun setup() {
        appContext.database.use {
            dropTable("TABLE1")
            createTable("TABLE1", true,
                    "MYID" to INTEGER + PRIMARY_KEY,
                    "QUOTE" to TEXT)
        }
    }

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("jarofawesome.app.niklas.jarofawesome", appContext.packageName)
    }

    @Test
    fun testOpenSaveDialog() {
        onView(withId(R.id.addQuote)).perform(click())
        onView(withHint("Something Awesome")).check(matches(isDisplayed()))
    }

    @Test
    fun testButton() {
        try {
            onView(withText("Button")).perform(click())
            fail()
        } catch (_ : NoMatchingViewException) {
        }
        saveRecord("New")
        onView(withId(R.id.showQuote)).check(matches(isDisplayed()))
    }

    @Test
    fun testSaveNewRecord() {
        onView(withId(R.id.showQuote)).check(matches(not(isDisplayed())))
        saveRecord("News Record")
        onView(withId(R.id.showQuote)).check(matches(isDisplayed()))
        onView(withId(R.id.showQuote)).perform(click())
        activity containsToast "News Record"
    }

    @Test
    fun testRotate(){
        onView(withId(R.id.addQuote)).perform(click())
        onView(withHint("Something Awesome")).perform(typeText("Hallo"))

        val device = UiDevice.getInstance(getInstrumentation())
        device.setOrientationLeft()

        onView(withText("Hallo")).check(matches(isDisplayed()))
    }

    @Test
    fun testRandom() {
        saveRecord("1")
        saveRecord("2")

        var firstDisplayed = false
        var secondDisplayed = false
        var bothDisplayed = false

        for (i in 1..100) {
            onView(withId(R.id.showQuote)).perform(click())
            if (activity.getToastMessage() == "1") firstDisplayed = true else secondDisplayed = true
            if(firstDisplayed && secondDisplayed) {
                bothDisplayed = true
                break
            }
        }

        if(!bothDisplayed) {
            fail()
        }
    }

    private fun saveRecord(record: String) {
        onView(withId(R.id.addQuote)).perform(click())
        onView(withHint("Something Awesome")).perform(typeText(record))
        onView(withText("Save")).perform(click())
    }

}

infix fun ActivityTestRule<MainActivity>.containsToast(message: String) =
        onView(withText(message))
                .inRoot(withDecorView(not(activity.window.decorView)))
                .check(matches(isDisplayed()))

fun ActivityTestRule<MainActivity>.getToastMessage(): String {
    var which = ""

    //Eins der beiden wird fehlschlagen, und gibt so die Nachricht preis.
    onView(withText("1"))
            .withFailureHandler { _, _ -> which = "2" }
            .inRoot(withDecorView(not(activity.window.decorView)))
            .check(matches(isDisplayed()))

    onView(withText("2"))
            .withFailureHandler { _, _ -> which = "1" }
            .inRoot(withDecorView(not(activity.window.decorView)))
            .check(matches(isDisplayed()))

    return which
}
