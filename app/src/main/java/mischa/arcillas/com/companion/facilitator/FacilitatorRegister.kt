package mischa.arcillas.com.companion.facilitator

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_facilitator_register.*
import kotlinx.android.synthetic.main.activity_seeker_register.*
import mischa.arcillas.com.companion.R
import java.text.SimpleDateFormat
import java.util.*

class FacilitatorRegister : AppCompatActivity() {

    private val myCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilitator_register)

        val birthDate = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        edtBirthdayFaci.setOnClickListener {
            DatePickerDialog(this, birthDate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        var tempFaci: String
        spinnerGenderFaci.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.gender))
        spinnerGenderFaci.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tempFaci = spinnerGenderFaci.selectedItem.toString()
            }
        }
        tempFaci = spinnerGenderFaci.selectedItem.toString()

        btnNextFaci.setOnClickListener {
            if (!(edtFnameFaci.text.isEmpty() && edtLnameFaci.text.isEmpty() && edtEmailFaci.text.isEmpty() && edtUsernameFaci.text.isEmpty() && edtPasswordFaci.text.isEmpty()
                            && edtConfirmFaci.text.isEmpty() && edtBirthdayFaci.text.isEmpty())) {
                val userTypeFaci = "facilitator"
                val fnameFaci = edtFnameFaci.text.toString()
                val lnameFaci = edtLnameFaci.text.toString()
                val emailFaci = edtEmailFaci.text.toString()
                val usernameFaci = edtUsernameFaci.text.toString()
                val passwordFaci = edtPasswordFaci.text.toString()
                val confirmFaci = edtConfirmFaci.text.toString()
                val bdayFaci = edtBirthdayFaci.text.toString()
                val genderFaci = tempFaci

                val intent = Intent(this, FacilitatorInterests::class.java)
                intent.putExtra("userTypeFaci", userTypeFaci)
                intent.putExtra("fnameFaci", fnameFaci)
                intent.putExtra("lnameFaci", lnameFaci)
                intent.putExtra("emailFaci", emailFaci)
                intent.putExtra("usernameFaci", usernameFaci)
                intent.putExtra("passwordFaci", passwordFaci)
                intent.putExtra("confirmFaci", confirmFaci)
                intent.putExtra("birthdayFaci", bdayFaci)
                intent.putExtra("genderFaci", genderFaci)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Fill-up everything", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun updateLabel() {
        val format = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(format, Locale.US)
        edtBirthdayFaci.setText(sdf.format(myCalendar.time))
    }
}
