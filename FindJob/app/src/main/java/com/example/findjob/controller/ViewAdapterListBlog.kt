package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterListBlog(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterListBlog.Detail>() {

    var imgListBlog: ImageView?= null
    var txtListTitleBlog: TextView?= null
    var txtListTopicBlog: TextView?= null
    var txtListTimeBlog: TextView?= null
    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imgListBlog = itemView.findViewById(R.id.img_list_blog)
            txtListTitleBlog = itemView.findViewById(R.id.txt_list_title_blog)
            txtListTopicBlog = itemView.findViewById(R.id.txt_list_topic_blog)
            txtListTimeBlog = itemView.findViewById(R.id.txt_list_time_blog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_profile_list_blog,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imgListBlog?.setImageResource(ds[position].imgblog)
            txtListTitleBlog?.text = ds[position].titleblog
            txtListTopicBlog?.text = "Chủ đề: ${ds[position].topicblog}"
            txtListTimeBlog?.text = "Vào lúc: ${ds[position].timeblog}"
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}