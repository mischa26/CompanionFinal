package mischa.arcillas.com.companion.profile_facilitator



import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_journey.*
import mischa.arcillas.com.companion.R
import java.util.*

class JourneyFaciActivity: Fragment(){

    var listElements = arrayOf<String>()

    private val url = "http;//172.17.2.51:8000/posts/save"

    lateinit var listElementsArrayList: MutableList<String>
    lateinit var adapterPost: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_journey, container, false)

        val post = view.findViewById<EditText>(R.id.edtPost)
        val btnPosted = view.findViewById<Button>(R.id.btnPost)
        val listPost = view.findViewById<ListView>(R.id.listPost)

        listElementsArrayList = ArrayList<String>(Arrays.asList(*listElements))
        adapterPost = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, listElementsArrayList)
        /*adapter = ArrayAdapter<String>(this@JourneyActivity, android.R.layout.simple_list_item_1, listElementsArrayList)*/
        listPost.adapter = adapterPost

        btnPosted.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                listElementsArrayList.add(edtPost.text.toString())
                adapterPost.notifyDataSetChanged()
                post.setText("")
            }
        })
        return view
    }
}