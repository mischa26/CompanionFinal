package mischa.arcillas.com.companion.facilitator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.CheckBox
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_facilitator_interests.*
import kotlinx.android.synthetic.main.activity_seeker_interests.*
import kotlinx.android.synthetic.main.interests_row.*
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.adapter.FaciInterestsAdapter
import mischa.arcillas.com.companion.adapter.InterestsAdapter
import mischa.arcillas.com.companion.model.InterestsData
import okhttp3.*
import java.io.IOException

class FacilitatorInterests : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilitator_interests)

        recycler_view_main_faci.layoutManager = LinearLayoutManager(this)

        fetchFaciInterests()

        btnNext2ndFaci.setOnClickListener {
            val chckInterest = findViewById<CheckBox>(R.id.chckInterest)

            val intent = Intent(this, FacilitatorSpecialization::class.java)
            intent.putExtra("interets", chckInterest.isChecked)

            startActivity(intent)
        }
    }

    fun fetchFaciInterests() {
        val url = "http://172.17.2.51:8000/interests/get"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val interest = gson.fromJson(body, InterestsData::class.java)

                runOnUiThread {
                    recycler_view_main_faci.adapter = FaciInterestsAdapter(interest)
                }
            }
            override fun onFailure(call: Call?, e: IOException?) {
            }
        })
    }
}
