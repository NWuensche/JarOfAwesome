package jarofawesome.app.niklas.jarofawesome

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import jarofawesome.app.niklas.jarofawesome.R.id.action_settings
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.customView
import org.jetbrains.anko.editText
import org.jetbrains.anko.verticalLayout
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private var dbHelper by Delegates.notNull<MyDatabaseOpenHelper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        dbHelper = MyDatabaseOpenHelper(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            alert {
                customView {
                    verticalLayout {
                        val newRecord = editText {
                            hint = "Something Awesome"
                        }
                        positiveButton("Save") { saveToDB(newRecord.text.toString()) }
                    }
                }
            }.show()
        }

        //TODO Update Tests
        //TODO 2. Activity weg
        //TODO SQLite mit Anko
        //TODO END Nur wichtige Anko Sachen importieren, nicht alle





    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.itemId == action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun saveToDB(newRecord: String) {
        val db = dbHelper.writableDatabase

        val values = ContentValues()
        values.put("QUOTE", newRecord)
        val newRowId = db.insert("TABLE1", null, values)
        val test = 1
    }
}
