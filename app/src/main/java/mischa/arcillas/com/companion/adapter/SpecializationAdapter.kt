package mischa.arcillas.com.companion.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import kotlinx.android.synthetic.main.interests_row.view.*
import kotlinx.android.synthetic.main.specialization_row.view.*
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.model.FaciInfo
import mischa.arcillas.com.companion.model.SpecializationData

class SpecializationAdapter(val specList: SpecializationData, faciInfo: FaciInfo): RecyclerView.Adapter<CustomViewHolderSpecialization>(){

    companion object {
        var tempSpec : MutableList<String> = arrayListOf()
    }

    //number of items
    override fun getItemCount(): Int {
        return specList.specs.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderSpecialization {
        //how do we create view
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.specialization_row, parent, false)
        return CustomViewHolderSpecialization(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolderSpecialization, position: Int) {
        val spec = specList.specs.get(position)
        holder?.view?.chckSpecialization.text = spec.spec_name

        holder.checkSpec.setOnCheckedChangeListener { view, isChecked ->
            if(holder.view.chckInterest.isChecked){
                tempSpec.add(spec.spec_name)
            }else{
                tempSpec.remove(spec.spec_name)
            }
        }
    }
}

class CustomViewHolderSpecialization (val view: View): RecyclerView.ViewHolder(view){
    val checkSpec = view.findViewById<CheckBox>(R.id.chckSpecialization)
}