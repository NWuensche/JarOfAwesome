package jarofawesome.app.niklas.jarofawesome

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * @author nwuensche
 */

@RunWith(AndroidJUnit4::class)
abstract class SuperEspresso {
    @Rule @JvmField val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)
    val appContext: Context = InstrumentationRegistry.getTargetContext()
}

