package mischa.arcillas.com.companion.facilitator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_facilitator_interests.*
import kotlinx.android.synthetic.main.activity_facilitator_specialization.*
import kotlinx.android.synthetic.main.activity_seeker_interests.*
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.adapter.SpecializationAdapter
import mischa.arcillas.com.companion.model.SpecializationData
import okhttp3.*
import java.io.IOException

class FacilitatorSpecialization : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilitator_specialization)

        recycler_view_main_spec.layoutManager = LinearLayoutManager(this)

        fetchSpecialization()

        btnNext3rdFaci.setOnClickListener {
            val intent = Intent(this, FacilitatorCertificate::class.java)
            startActivity(intent)
        }
    }

    fun fetchSpecialization() {
        val url = "http://172.17.2.51:8000/specialization/get"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val specs = gson.fromJson(body, SpecializationData::class.java)

                runOnUiThread {

                    recycler_view_main_spec.adapter = SpecializationAdapter(specs)
                }
            }
            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }
}

