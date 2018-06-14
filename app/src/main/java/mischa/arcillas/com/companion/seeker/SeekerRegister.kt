package mischa.arcillas.com.companion.seeker

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_seeker_register.*
import mischa.arcillas.com.companion.R
import java.text.SimpleDateFormat
import java.util.*

class SeekerRegister : AppCompatActivity() {


    private val myCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeker_register)

        val birthDate =DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        edtBirthday.setOnClickListener {
            DatePickerDialog(this, birthDate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        var temp: String
        spinnerGender.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.gender))
        spinnerGender.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                temp = spinnerGender.selectedItem.toString()
            }
        }
        temp = spinnerGender.selectedItem.toString()

        btnNext.setOnClickListener {
            if (!(edtFname.text.isEmpty() && edtLname.text.isEmpty() && edtEmail.text.isEmpty() && edtUsername.text.isEmpty() && edtPassword.text.isEmpty()
                            && edtConfirm.text.isEmpty() && edtBirthday.text.isEmpty())) {

                val userType = "seeker"
                val fname = edtFname.text.toString()
                val lname = edtLname.text.toString()
                val email = edtEmail.text.toString()
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()
                val confirm = edtConfirm.text.toString()
                val bday = edtBirthday.text.toString()
                val gender = temp

                val i = Intent(this@SeekerRegister, SeekerInterests::class.java)
                i.putExtra("userType", userType)
                i.putExtra("fname", fname)
                i.putExtra("lname", lname)
                i.putExtra("email", email)
                i.putExtra("username", username)
                i.putExtra("password", password)
                i.putExtra("confirm", confirm)
                i.putExtra("birthday", bday)
                i.putExtra("gender", gender)
                startActivity(i)
            }else{
                Toast.makeText(this, "Fill-up everything", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun updateLabel() {
        val format = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(format, Locale.US)
        edtBirthday.setText(sdf.format(myCalendar.time))
    }
}
