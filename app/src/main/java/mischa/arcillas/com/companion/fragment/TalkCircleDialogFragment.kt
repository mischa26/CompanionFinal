package mischa.arcillas.com.companion.fragment

import android.app.Dialog
import android.app.DialogFragment
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mischa.arcillas.com.companion.R

class TalkCircleDialogFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.custom_dialog, container, false)

        return view
    }
}
