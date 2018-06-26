package mischa.arcillas.com.companion

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_login.*
import mischa.arcillas.com.companion.local_db.LocalDB
import mischa.arcillas.com.companion.local_db.LocalDatabaseHandler
import mischa.arcillas.com.companion.local_db.UserLocal
import mischa.arcillas.com.companion.model.Name
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.json.JSONObject

import java.io.IOException

class Login : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtSign2.setOnClickListener {
            val intent = Intent(this, Choices::class.java)
            startActivity(intent)
        }

        /*btnLogin2.setOnClickListener {
            val i = Intent(this, Home::class.java)
            startActivity(i)
        }*/

        btnLogin2.setOnClickListener {
            doAsync {
                /*val result = "http://192.168.1.10:8000/login"*/
                /*val result = "http://192.168.1.8:8000/login"*/
                val result = "http://172.17.2.132:8000/login"
                val mClient = OkHttpClient()
                val jsonObj = JSONObject()

                jsonObj.put("username", edtUser1.text.toString())
                jsonObj.put("password", edtpass1.text.toString())

                val JSON = MediaType.parse("application/json; charset=utf-8")
                val body = RequestBody.create(JSON, jsonObj.toString())

                val mRequest = Request.Builder()
                        .url(result)
                        .post(body)
                        .header("Authorization", "token")
                        .addHeader("Content-Type", "application/json")
                        .build()
                mClient.newCall(mRequest).enqueue(object : Callback {
                    override fun onFailure(call: Call?, e: IOException?) {
                        if (e != null) {
                            Log.e("hahaha", e.message)
                        }
                    }

                    override fun onResponse(call: Call?, response: Response?) {
                        val body = response?.body()?.string()
                        val gson = GsonBuilder().create()

                        val bodyName = gson.fromJson(body, Name::class.java)
                        println(bodyName)
                        Log.i("hahah", body)

                        val localUserDb = LocalDatabaseHandler(this@Login)
                        localUserDb.insertData(bodyName.name, bodyName.token)
                        val iUser = Intent(this@Login, LocalDB::class.java)
                        iUser.putExtra("name", bodyName.name)
                        iUser.putExtra("token", bodyName.token)
                        startActivity(iUser)

                        val i = Intent(this@Login, Home::class.java)
                        i.putExtra("name", bodyName.name)
                        i.putExtra("token", bodyName.token)
                        startActivity(i)


                    }
                })
            }
        }
    }
}


