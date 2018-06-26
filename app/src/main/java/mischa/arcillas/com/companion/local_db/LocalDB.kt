package mischa.arcillas.com.companion.local_db

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LocalDB : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val context = this

        btnLogin2.setOnClickListener({
            if (edtUser1.text.toString().isNotEmpty() && edtpass1.text.toString().isNotEmpty()){
                val extra = intent.extras
                val name_db = extra.getString("name")
                val token_db = extra.getString("token")

                var db = LocalDatabaseHandler(context)
                db.insertData(name_db, token_db)
                /*var user = UserLocal(edtUser1.text.toString(), edtpass1.text.toString())
                var db = LocalDatabaseHandler(context)
                db.insertData(user)
                println(user)*/
            }else{
                Toast.makeText(context, "Fill All Data's", Toast.LENGTH_SHORT).show()
            }
        })
    }
}