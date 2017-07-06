package jarofawesome.app.niklas.jarofawesome

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(RobolectricTestRunner::class)
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
        dbHelper.setQuote(testStr1)
        assertTrue(dbHelper.contains(testStr1))
        assertEquals(dbHelper.rowCount(), 1)
        dbHelper.setQuote("testing2")
        assertEquals(dbHelper.rowCount(), 2)
    }
}