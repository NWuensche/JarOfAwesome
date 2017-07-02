package jarofawesome.app.niklas.jarofawesome

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.db.dropTable
import org.jetbrains.anko.db.insert

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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

        hello.visibility = if(database.rowCount() > 0) View.VISIBLE else View.GONE

        hello.setOnClickListener {
            toast(database.randomQuote())
        }



        //TODO Update Tests
        //TODO END Nur wichtige Anko Sachen importieren, nicht alle




    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun saveToDB(newRecord: String) {
        database.setQuote(newRecord)
        hello.visibility = View.VISIBLE
    }
}
