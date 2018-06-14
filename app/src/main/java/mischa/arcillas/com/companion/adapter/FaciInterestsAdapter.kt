package mischa.arcillas.com.companion.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import kotlinx.android.synthetic.main.interests_row.view.*
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.model.InterestsData
import mischa.arcillas.com.companion.model.UserInfo

class FaciInterestsAdapter(val interestList: InterestsData): RecyclerView.Adapter<CustomViewHolderFaci>(){

    companion object {
        var tempInterest : ArrayList<String> = arrayListOf()
    }

    override fun getItemCount(): Int {
        return interestList.interests.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderFaci{
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.interests_row, parent, false)
        return CustomViewHolderFaci(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolderFaci, position: Int) {
        val interest =  interestList.interests.get(position)
        holder?.view?.chckInterest?.text = interest.interestName

        holder.checkInterest.setOnCheckedChangeListener { view, isChecked ->
            if(holder.view.chckInterest.isChecked){
                tempInterest.add(interest.interestName)
            }else{
                tempInterest.remove(interest.interestName)
            }
        }
    }
}
class CustomViewHolderFaci(val view: View): RecyclerView.ViewHolder(view){
    val checkInterest = view.findViewById<CheckBox>(R.id.chckInterest)
}