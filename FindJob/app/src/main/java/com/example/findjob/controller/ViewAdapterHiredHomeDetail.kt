package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterHiredHomeDetail(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterHiredHomeDetail.Detail>() {

    var image: ImageView?= null
    var txtNameJob: TextView?= null
    var txtType: TextView?= null
    var txtCount: TextView?=null
    var txtTinh: TextView?= null
    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            image = itemView.findViewById(R.id.img_hired_home_detail)
            txtNameJob = itemView.findViewById(R.id.txtNameJob_home_detail)
            txtType = itemView.findViewById(R.id.txtType_home_detail)
            txtCount = itemView.findViewById(R.id.txtCount_home_detail)
            txtTinh = itemView.findViewById(R.id.txtTinh_home_detail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_hired_home_detail,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            image?.setImageResource(ds[position].image)
            txtNameJob?.text = ds[position].tenCongViec
            txtType?.text = ds[position].theLoai
            txtCount?.text = ds[position].soLuong.toString()
            txtTinh?.text = ds[position].diaChi
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return  ds.size
    }
}