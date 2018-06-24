package mischa.arcillas.com.companion.fragment

import android.app.Dialog
import android.app.DialogFragment
import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import mischa.arcillas.com.companion.R

class TalkCircleDialogFragment: DialogFragment() {

    //widgets
    private var mInput: CheckBox?=null
    private var mActionOk: TextView?=null
    private var mActionCancel: TextView?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.custom_dialog, container, false)

        /*mInput = view.findViewById(R.id.chckSpecialization)
        mActionOk = view.findViewById(R.id.action_ok)
        mActionCancel = view.findViewById(R.id.action_cancel)

        mActionCancel!!.setOnClickListener {
            Log.d("hehe", "ts")
            dialog.dismiss()
        }
        mActionOk!!.setOnClickListener {

        }*/
        return view
    }
}
