package mischa.arcillas.com.companion.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mischa.arcillas.com.companion.R


class NotifFragment : Fragment(){

    companion object {
        fun newInstance(): Fragment {
            var fb: NotifFragment = NotifFragment()
            return fb
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_notif, container,false)
    }
}
