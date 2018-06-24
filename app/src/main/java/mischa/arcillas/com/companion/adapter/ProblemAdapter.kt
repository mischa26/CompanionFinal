package mischa.arcillas.com.companion.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import kotlinx.android.synthetic.main.problems_row.view.*
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.model.ProblemsData

/*class ProblemAdapter(val problemList: ProblemsData): RecyclerView.Adapter<CustomViewHolderProblems> (){
    companion object {
        var tempProblems: MutableList<String> = arrayListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderProblems{
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.problems_row, parent, false)
        return CustomViewHolderProblems(cellForRow)
    }

    override fun getItemCount(): Int {
        return problemList.problems.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolderProblems, position: Int) {
        val problem = problemList.problems.get(position)
        holder?.view?.chckProblems?.text = problem.problem_name

        holder.checkProblem.setOnCheckedChangeListener { view, isChecked ->
            if (holder.view.chckProblems.isChecked){
                tempProblems.add(problem.problem_name)
            }else{
                tempProblems.remove(problem.problem_name)
            }
        }
    }

}

class CustomViewHolderProblems(val view: View): RecyclerView.ViewHolder(view) {
    val checkProblem = view.findViewById<CheckBox>(R.id.chckProblems)
}*/
