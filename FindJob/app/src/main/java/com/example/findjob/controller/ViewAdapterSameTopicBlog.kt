package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterSameTopicBlog(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterSameTopicBlog.Detail>(){


    var imageBlog: ImageView?= null
    var txtTitleBlog: TextView?= null
    var txtTimeBlog: TextView?= null

    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imageBlog = itemView.findViewById(R.id.img_same_topic_blog)
            txtTitleBlog = itemView.findViewById(R.id.txt_same_title_topic_blog)
            txtTimeBlog = itemView.findViewById(R.id.txt_same_time_topic_blog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_same_topic_blog,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imageBlog?.setImageResource(ds[position].imgblog)
            txtTitleBlog?.text = ds[position].titleblog
            txtTimeBlog?.text = "Ngày đăng tải: ${ds[position].timeblog}"
            txtTitleBlog?.maxLines = 2
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}