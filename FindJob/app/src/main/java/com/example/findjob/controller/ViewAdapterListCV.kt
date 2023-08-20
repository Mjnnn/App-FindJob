package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterListCV(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterListCV.Detail>() {

    var imgCv: ImageView?= null
    var txtTitleCv: TextView?= null
    var txtTopicCv: TextView?= null
    var txtTimeCv: TextView?= null
    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imgCv = itemView.findViewById(R.id.img_cv)
            txtTitleCv = itemView.findViewById(R.id.txt_title_cv)
            txtTopicCv = itemView.findViewById(R.id.txt_topic_cvc)
            txtTimeCv = itemView.findViewById(R.id.txt_time_cv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_form_cv,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imgCv?.setImageResource(ds[position].imgcv)
            txtTitleCv?.text = ds[position].titlecv
            txtTopicCv?.text = "Mẫu thiết kế: ${ds[position].topic}"
            txtTimeCv?.text = "Thời gian đăng tải: ${ds[position].time}"
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}
