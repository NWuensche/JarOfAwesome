package jarofawesome.app.niklas.jarofawesome

import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.jetbrains.anko.db.*
import java.util.*

class MyDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, MyDatabaseOpenHelper.DATABASE_NAME, null, MyDatabaseOpenHelper.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db?.createTable("TABLE1", true,
                "MYID" to INTEGER + PRIMARY_KEY,
                "QUOTE" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db?.dropTable("TABLE1", true)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun rowCount(): Int =
        use {
            select("TABLE1").exec {
                count
            }
        }

    fun randomQuote(): String =
        use {
            val rows = rowCount()
            select("TABLE1", "QUOTE").exec {
                asSequence()
                        .elementAt((Math.random() * rows).toInt())[0] as String
            }
        }

    fun setQuote(quote: String) =
            use {
                insert("TABLE1",
                        "QUOTE" to quote)
            }

    fun contains(quote: String) =
        use{
            select("TABLE1").whereArgs("QUOTE = '$quote'").exec {
                asSequence()
                        .any()
            }
        }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "QUOTES"
        private var instance: MyDatabaseOpenHelper? = null

            @Synchronized
            fun getInstance(ctx: Context): MyDatabaseOpenHelper {
                if (instance == null) {
                    instance = MyDatabaseOpenHelper(ctx.applicationContext)
                }
                return instance!!
            }
        }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(getApplicationContext())
