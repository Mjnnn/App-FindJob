package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterHiredTCKDHomeSearch(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterHiredTCKDHomeSearch.Detail>() {

    var imageTCKD: ImageView?= null
    var txtNameJobTCKD: TextView?= null
    var txtMucLuongTCKD: TextView?= null

    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imageTCKD = itemView.findViewById(R.id.img_hired_tckd_home_search)
            txtNameJobTCKD = itemView.findViewById(R.id.txtNameJobb_hired_tckd_home_search)
            txtMucLuongTCKD = itemView.findViewById(R.id.txtMucLuong_hired_tckd_home_search)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_hiredtckd_home_search,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imageTCKD?.setImageResource(ds[position].image)
            txtNameJobTCKD?.text = ds[position].tenCongViec
            txtMucLuongTCKD?.text = "Mức lương: ${ds[position].mucluong}$"
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}