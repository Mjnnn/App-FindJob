package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterNotification(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterNotification.Detail>() {

    var imageNotification: ImageView?= null
    var txtTitle: TextView?= null
    var txtUsername: TextView?=null
    var txtTimeNotification: TextView?= null
    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imageNotification = itemView.findViewById(R.id.img_notification)
            txtTitle = itemView.findViewById(R.id.txt_notification2)
            txtUsername = itemView.findViewById(R.id.txt_notification1)
            txtTimeNotification = itemView.findViewById(R.id.txt_time_notification)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_notification,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imageNotification?.setImageResource(ds[position].imgavata)
            txtTitle?.text = ds[position].topic
            txtUsername?.text = "${ds[position].usernametopic}"
            txtTimeNotification?.text = "Vào lúc: ${ds[position].time}"

            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}