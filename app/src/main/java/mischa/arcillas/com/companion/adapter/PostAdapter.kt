package mischa.arcillas.com.companion.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.post_row.view.*
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.mode.PostData

class PostAdapter(val postList: List<PostData>): RecyclerView.Adapter<CustomViewHolderPost>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderPost{
       val layoutInflater = LayoutInflater.from(parent?.context)
       val cellForRow = layoutInflater.inflate(R.layout.post_row, parent, false)
       return CustomViewHolderPost(cellForRow)
    }

    override fun getItemCount(): Int {
       return postList.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolderPost, position: Int) {
       val posts: PostData = postList[position]
       holder?.view?.txt_post?.text = posts.post
       holder?.view?.txtFeeling?.text = posts.feeling
    }
}

class CustomViewHolderPost(val view: View): RecyclerView.ViewHolder(view){

}

