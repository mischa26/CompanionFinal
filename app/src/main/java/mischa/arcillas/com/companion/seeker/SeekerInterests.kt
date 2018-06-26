package mischa.arcillas.com.companion.seeker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_seeker_interests.*
import mischa.arcillas.com.companion.Login
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.adapter.InterestsAdapter
import mischa.arcillas.com.companion.adapter.InterestsAdapter.Companion.tempInterest
import mischa.arcillas.com.companion.model.InterestsData
import mischa.arcillas.com.companion.model.UserInfo
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class SeekerInterests : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeker_interests)

        recycler_view_main_seeker.layoutManager = LinearLayoutManager(this)

        val extras = intent.extras
        if (extras != null) {
            val userType = extras.getString("userType")
            val firstname = extras.getString("fname")
            val lastname = extras.getString("lname")
            val email = extras.getString("email")
            val username = extras.getString("username")
            val password = extras.getString("password")
            val confirm = extras.getString("confirm")
            val birthday = extras.getString("birthday")
            val gender = extras.getString("gender")
            val addUser = UserInfo(userType, firstname, lastname, email, username, password,
                    confirm, birthday, gender)

            btnSignUpSeeker.setOnClickListener {

                /*for (i in 0 until tempInterest.size) {
                    interstString = InterestsSeeker(tempInterest[i])
                }*/

                doAsync {
                    /*val result = "http://192.168.1.8:8000/registerService"*/
                    val result = "http://172.17.1.133:8000/registerService"
                    val mClient = OkHttpClient()
                    val jsonObj = JSONObject()

                    jsonObj.put("userType", userType.toString())
                    jsonObj.put("fname", firstname.toString())
                    jsonObj.put("lname", lastname.toString())
                    jsonObj.put("email", email.toString())
                    jsonObj.put("username", username.toString())
                    jsonObj.put("password", password.toString())
                    jsonObj.put("confirm", confirm.toString())
                    jsonObj.put("birthday", birthday.toString())
                    jsonObj.put("gender", gender.toString())

                    val interestsArr = JSONArray()

                    tempInterest.forEach {
                        interestsArr.put(it)
                    }

                    jsonObj.put("interests", interestsArr)

                    val JSON = MediaType.parse("application/json; charset=utf-8")
                    val body = RequestBody.create(JSON, jsonObj.toString())

                    val mRequest = Request.Builder()
                            .url(result)
                            .post(body)
                            .header("Authorization", "token")
                            .addHeader("Content-Type", "application/json")
                            .build()
                    mClient.newCall(mRequest).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            Log.e("hahaha", e.message)
                        }
                        override fun onResponse(call: Call, response: Response) {
                            val body = response.body()?.string()
                            Log.e("Mischa",body)
                            val i = Intent (this@SeekerInterests, Login::class.java)
                            startActivity(i)
                        }
                    })
                }
            }
            fetchInterest(addUser)
        }
    }

    private fun fetchInterest(userInfo: UserInfo) {
        /*val url = "http://192.168.1.8:8000/interests/get"
        *//*val url = "http://192.168.1.8:8000/interests/get"*/
        val url = "http://172.17.2.132:8000/interests/get"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val interest = gson.fromJson(body, InterestsData::class.java)

                runOnUiThread {
                    recycler_view_main_seeker.adapter = InterestsAdapter(interest, userInfo)
                }
            }
            override fun onFailure(call: Call?, e: IOException?) {
            }
        })
    }
}


