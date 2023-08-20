package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterHiredITHomeSearch(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterHiredITHomeSearch.Detail>() {

    var imageIt: ImageView?= null
    var txtNameJobIt: TextView?= null
    var txtMucLuongIt: TextView?= null

    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imageIt = itemView.findViewById(R.id.img_hired_it_home_search)
            txtNameJobIt = itemView.findViewById(R.id.txtNameJobb_hired_it_home_search)
            txtMucLuongIt = itemView.findViewById(R.id.txtMucLuong_hired_it_home_search)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_hiredit_home_search,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imageIt?.setImageResource(ds[position].image)
            txtNameJobIt?.text = ds[position].tenCongViec
            txtMucLuongIt?.text = "Mức lương: ${ds[position].mucluong}$"
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}