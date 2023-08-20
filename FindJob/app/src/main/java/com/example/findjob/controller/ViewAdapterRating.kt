package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterRating(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterRating.Detail>() {

    var imgRating: ImageView?= null
    var txtUsername: TextView?= null
    var txtContent: TextView?= null
    var txtTime: TextView?= null
    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imgRating = itemView.findViewById(R.id.img_raing)
            txtUsername = itemView.findViewById(R.id.txt_username_rating)
            txtContent = itemView.findViewById(R.id.txt_content_rating)
            txtTime = itemView.findViewById(R.id.txt_time_rating)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rating,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imgRating?.setImageResource(ds[position].imgavata)
            txtUsername?.text = ds[position].username
            txtContent?.text = ds[position].content
            txtTime?.text = "Vào lúc: ${ds[position].time}"
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}