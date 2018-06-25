package mischa.arcillas.com.companion.local_db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "Companion"
val TABLE_NAME = "Users"
val COL_NAME = "name"
val COL_TOKEN = "token"

class LocalDatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE" + TABLE_NAME + "(" + COL_TOKEN +
                "INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + "VARCHAR(256)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(user: UserLocal){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, user.name)
        cv.put(COL_TOKEN, user.token)
        var result= db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
}
