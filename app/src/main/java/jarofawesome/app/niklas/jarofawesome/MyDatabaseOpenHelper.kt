package jarofawesome.app.niklas.jarofawesome

import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.jetbrains.anko.db.*

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

fun MyDatabaseOpenHelper.rowCount(): Int =
        use {
            select("TABLE1").exec {
                count
            }
        }