package mischa.arcillas.com.companion.fragment


import android.app.AlertDialog
import android.app.Dialog
import android.app.Fragment
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import mischa.arcillas.com.companion.adapter.PostAdapter
import mischa.arcillas.com.companion.mode.PostData
import mischa.arcillas.com.companion.model.FeelingsData
import okhttp3.*
import org.jetbrains.anko.runOnUiThread
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

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

        var btnPosted: Button
        btnPosted = view.findViewById(R.id.btnPost)

        btnPosted.setOnClickListener {
            val pRecylerView = view.findViewById<RecyclerView>(R.id.recyler_view_post)
            val pLayoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            val posts = ArrayList<PostData>()
            val pAdapter = PostAdapter(posts)

            val txtPost = view.findViewById<EditText>(R.id.editPost)

            posts.add(PostData(editPost.text.toString(), temp))
            txtPost.setText("")

            pRecylerView?.layoutManager = pLayoutManager
            pRecylerView?.adapter = pAdapter
        }
        return view
    }
}




