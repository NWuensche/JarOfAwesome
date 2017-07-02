package jarofawesome.app.niklas.jarofawesome

import android.os.Build.VERSION_CODES.LOLLIPOP
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk =
intArrayOf(android.os.Build.VERSION_CODES.N), packageName = "jarofawesome.app.niklas.jarofawesome")
class ExampleUnitTest {
    lateinit var dbHelper: MyDatabaseOpenHelper

    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Before
    fun setup() {
        dbHelper = MyDatabaseOpenHelper(RuntimeEnvironment.application)
    }

    @Test
    @Throws(Exception::class)
    fun testDbInsertion() {
        val testStr1 = "testing"
        val testStr2 = "testing"
        dbHelper.insertText(testStr1)
        dbHelper.insertText(testStr2)
        assertEquals(dbHelper.getAllText(),
                "$testStr1-$testStr2-")
    }
}