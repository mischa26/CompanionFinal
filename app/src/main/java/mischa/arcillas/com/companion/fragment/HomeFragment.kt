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
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_home.*
import mischa.arcillas.com.companion.R

class HomeFragment : Fragment(){

    companion object {
        fun newInstance(): Fragment{
            var fb: HomeFragment = HomeFragment()
            return fb
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_home, container, false)

        btnTalkCircle.setOnClickListener {

        }
        return view
    }
}



