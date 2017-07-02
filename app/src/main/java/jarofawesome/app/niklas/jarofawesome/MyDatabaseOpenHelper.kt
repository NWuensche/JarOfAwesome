package jarofawesome.app.niklas.jarofawesome

import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseOpenHelper(context: Context) : SQLiteOpenHelper(context, MyDatabaseOpenHelper.DATABASE_NAME, null, MyDatabaseOpenHelper.DATABASE_VERSION) {
    private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + "TABLE1" + " (" +
                    "MYID" + " INTEGER PRIMARY KEY," +
                    "QUOTE" + " TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + "TABLE1"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"
    }
}