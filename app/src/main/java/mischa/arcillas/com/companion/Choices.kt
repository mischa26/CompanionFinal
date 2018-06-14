package mischa.arcillas.com.companion

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choices.*
import mischa.arcillas.com.companion.facilitator.FacilitatorRegister
import mischa.arcillas.com.companion.seeker.SeekerRegister

class Choices : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choices)

        btnSeeker.setOnClickListener {
            val intent = Intent(this, SeekerRegister::class.java)
            startActivity(intent)
        }

        btnFacilitator.setOnClickListener {
            val intent = Intent(this, FacilitatorRegister::class.java)
            startActivity(intent)
        }
    }
}
