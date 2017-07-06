package jarofawesome.app.niklas.jarofawesome

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        addQuote.setOnClickListener {

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

        showQuote.visibility = if(database.rowCount() > 0) View.VISIBLE else View.GONE

        showQuote.setOnClickListener {
            toast(database.randomQuote())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun saveToDB(newRecord: String) {
        database.setQuote(newRecord)
        showQuote.visibility = View.VISIBLE
    }
}
