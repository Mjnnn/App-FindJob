package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterListJob(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterListJob.Detail>() {

    var imgListJob: ImageView?= null
    var txtListNameJob: TextView?= null
    var txtListLocationJob: TextView?= null
    var txtListCountJob: TextView?= null
    var txtListTimeJob: TextView?= null
    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imgListJob = itemView.findViewById(R.id.img_list_job)
            txtListNameJob = itemView.findViewById(R.id.txt_list_name_job)
            txtListCountJob = itemView.findViewById(R.id.txt_list_count_job)
            txtListLocationJob = itemView.findViewById(R.id.txt_list_location_job)
            txtListTimeJob = itemView.findViewById(R.id.txt_list_time_job)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_profile_list_job,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imgListJob?.setImageResource(ds[position].image)
            txtListNameJob?.text = ds[position].tenCongViec
            txtListLocationJob?.text = "Địa chỉ: ${ds[position].diaChi}"
            txtListCountJob?.text = "Số lượng: ${ds[position].soLuong}"
            txtListTimeJob?.text = "Hạn nộp hồ sơ: ${ds[position].ngayketthuc}"
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}