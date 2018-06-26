package mischa.arcillas.com.companion.facilitator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_facilitator_interests.*
import kotlinx.android.synthetic.main.activity_facilitator_specialization.*
import kotlinx.android.synthetic.main.activity_seeker_interests.*
import mischa.arcillas.com.companion.Login
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.adapter.SpecializationAdapter
import mischa.arcillas.com.companion.adapter.SpecializationAdapter.Companion.tempSpec
import mischa.arcillas.com.companion.model.FaciInfo
import mischa.arcillas.com.companion.model.SpecializationData
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class FacilitatorSpecialization : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilitator_specialization)

        recycler_view_main_spec.layoutManager = LinearLayoutManager(this)


        val extras = intent.extras
        if (extras != null){

            val userTypeFaci = extras.getString("userTypeFaci")
            val firstNameFaci = extras.getString("fnameFaci")
            val lastNameFaci = extras.getString("lnameFaci")
            val emailFaci = extras.getString("emailFaci")
            val usernameFaci = extras.getString("usernameFaci")
            val passwordFaci = extras.getString("passwordFaci")
            val confirmFaci = extras.getString("confirmFaci")
            val birthdayFaci = extras.getString("birthdayFaci")
            val genderFaci = extras.getString("genderFaci")
            val prcPhoto = extras.getString("prcPhoto")
            val interestsFaci = extras.getStringArrayList("interestsFaci")
            val addFaci = FaciInfo("facilitator", firstNameFaci, lastNameFaci, emailFaci, usernameFaci,
                    passwordFaci, confirmFaci, birthdayFaci, genderFaci, prcPhoto, interestsFaci)
            println(firstNameFaci+" "+lastNameFaci)


            btnSignUpFaci.setOnClickListener {
                /*val extras1 = intent.extras
                if (extras1 != null) {
                    val userTypeFaci1 = extras1.getString("userTypeFaci")
                    val firstNameFaci1 = extras1.getString("fnameFaci")
                    val lastNameFaci1 = extras1.getString("lnameFaci")
                    val emailFaci1 = extras1.getString("emailFaci")
                    val usernameFaci1 = extras1.getString("usernameFaci")
                    val passwordFaci1 = extras1.getString("passwordFaci")
                    val confirmFaci1 = extras1.getString("confirmFaci")
                    val birthdayFaci1 = extras1.getString("birthdayFaci")
                    val genderFaci1 = extras1.getString("genderFaci")
                    val prcPhoto1 = extras1.getString("prcPhoto")
                    val interestsFaci1 = extras1.getStringArrayList("interestsFaci")*/

                    doAsync {
                        val result = "http://172.17.2.132:8000/registerService"
                        /*val result = "http://172.17.2.51:8000/registerService"*/
                        val mClient = OkHttpClient()
                        val jsonObj = JSONObject()

                        jsonObj.put("userType","facilitator")
                        jsonObj.put("fname", firstNameFaci.toString())
                        jsonObj.put("lname", lastNameFaci.toString())
                        jsonObj.put("email", emailFaci.toString())
                        jsonObj.put("username", usernameFaci.toString())
                        jsonObj.put("password", passwordFaci.toString())
                        jsonObj.put("confirm", confirmFaci.toString())
                        jsonObj.put("birthday", birthdayFaci.toString())
                        jsonObj.put("gender", genderFaci.toString())
                        jsonObj.put("prcPhoto", prcPhoto.toString())
                        jsonObj.put("interests", interestsFaci.toString())

                        val specArr = JSONArray()
                        tempSpec.forEach {
                            specArr.put(it)
                        }
                        jsonObj.put("specs", specArr)

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
                                Log.e("hihihi", e.message)
                            }

                            override fun onResponse(call: Call, response: Response) {
                                val body = response.body()?.string()
                                Log.e("Arcillas", body)
                                val intent = Intent(this@FacilitatorSpecialization, Login::class.java)
                                startActivity(intent)
                            }
                        })
                    }
                }
            fetchSpecialization(addFaci)
        }
    }

    fun fetchSpecialization(faciInfo: FaciInfo) {
        /*val url = "http://192.168.1.10:8000/specialization/get"*/
        val url = "http://172.17.2.132:8000/specialization/get"
        /*val url = "http://172.17.2.51:8000/specialization/get"*/

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val specs = gson.fromJson(body, SpecializationData::class.java)

                runOnUiThread {

                    recycler_view_main_spec.adapter = SpecializationAdapter(specs, faciInfo)
                }
            }
            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }
}

