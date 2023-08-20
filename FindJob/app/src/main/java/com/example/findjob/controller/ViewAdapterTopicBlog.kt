package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterTopicBlog(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterTopicBlog.Detail>() {

    var imageBlog: ImageView?= null
    var txtTitleBlog: TextView?= null
    var txtContentBlog: TextView?= null
    var txtUsernameBlog: TextView?=null
    var txtTimeBlog: TextView?= null
    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imageBlog = itemView.findViewById(R.id.img_topic_blog)
            txtTitleBlog = itemView.findViewById(R.id.txt_title_topic_blog)
            txtContentBlog = itemView.findViewById(R.id.txt_content_topic_blog)
            txtUsernameBlog = itemView.findViewById(R.id.txt_username_topic_blog)
            txtTimeBlog = itemView.findViewById(R.id.txt_time_topic_blog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_topic_blog,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imageBlog?.setImageResource(ds[position].imgblog)
            txtTitleBlog?.text = ds[position].titleblog
            txtContentBlog?.text = ds[position].contentblog
            txtUsernameBlog?.text = "${ds[position].username}"
            txtTimeBlog?.text = "${ds[position].timeblog}"
            txtContentBlog?.maxLines = 2
//            txtContentBlog?.text = HtmlCompat.fromHtml(txtContentBlog.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}