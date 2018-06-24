package mischa.arcillas.com.companion.fragment


import android.app.AlertDialog
import android.app.Dialog
import android.app.Fragment
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_seeker_register.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.talk_circle_row.*
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.model.FeelingsData
import okhttp3.*
import org.jetbrains.anko.runOnUiThread
import java.io.IOException

class HomeFragment : Fragment(){

    companion object {
        fun newInstance(): Fragment{
            var fb: HomeFragment = HomeFragment()
            return fb
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_home, container, false)

        var temp: String
        var spin: Spinner
//        var setadapter: ArrayAdapter<String> = ArrayAdapter(activity, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.feelings))
//        spinnerFeelings.adapter = setadapter
        spin = view.findViewById(R.id.spinnerFeelings)
        spin.adapter = ArrayAdapter(activity, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.feelings))
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                temp =  spin.selectedItem.toString()
            }
        }
        temp = spin.selectedItem.toString()
       /* fetchFeelings()*/
        return view
    }

    /*fun fetchFeelings() {
        val url = "http://192.168.1.10:8000/feelings/get"
        val request =  Request.Builder().url(url).build()
        val client = OkHttpClient()


        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call?, response: Response?) {
               val body = response?.body()?.string()
               println(body)

                val gson = GsonBuilder().create()

                val feelings = gson.fromJson(body, FeelingsData::class.java)

                runOnUiThread {
                    var feelingsSpinner: MutableList<String> = arrayListOf()
//                    feelingsSpinner.add(feelings)

                }
            }

        })
    }*/
}




