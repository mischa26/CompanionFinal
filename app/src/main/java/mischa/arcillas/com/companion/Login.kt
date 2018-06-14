package mischa.arcillas.com.companion

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.io.IOException

class Login : AppCompatActivity() {


    /*val url = "http://192.168.1.8:8000/login"*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtSign2.setOnClickListener {
            val intent = Intent(this, Choices::class.java)
            startActivity(intent)
        }

        btnLogin2.setOnClickListener {
            doAsync {
                val result = "http://172.17.2.51:8000/login"
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
                mClient.newCall(mRequest).enqueue(object : Callback{
                    override fun onFailure(call: Call?, e: IOException?) {
                        if (e != null) {
                            Log.e("hahaha", e.message)
                        }
                    }
                    override fun onResponse(call: Call?, response: Response?) {
                        val body = response?.body()?.string()
                        Log.e("Mischa",body)
                        val i = Intent (this@Login, Home::class.java)
                        startActivity(i)
                    }

                })
            }
        }

        /*//Database
        val jsonObj = JSONObject()
        btnLogin2.setOnClickListener {
            jsonObj.put("username", edtUser1.text.toString())
            jsonObj.put("password", edtpass1.text.toString())

            val que = Volley.newRequestQueue(this)
            val req = JsonObjectRequest(Request.Method.POST, url, jsonObj,
                    Response.Listener { response ->
                        if(response != null) {
                            toast(response["message"].toString())
                            val intent = Intent(this, Home::class.java)
                            startActivity(intent)
                        }
                    }, Response.ErrorListener { error ->
                *//*Log.e("lalalalalala", error.message)*//*
                toast("Error" + error.message)
            })
            que.add(req)
        }*/
    }
}


