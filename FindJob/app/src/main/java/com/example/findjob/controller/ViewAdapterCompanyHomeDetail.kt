package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterCompanyHomeDetail(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterCompanyHomeDetail.Detail>() {

    var imageCompany: ImageView?= null
    var txtNameCompany: TextView?= null
    var txtTrangThaiCtyCompany: TextView?= null

    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imageCompany = itemView.findViewById(R.id.img_comapny_home_detail)
            txtNameCompany = itemView.findViewById(R.id.txtNameCompany_home_detail)
            txtTrangThaiCtyCompany = itemView.findViewById(R.id.txtTrangThaiCty_home_detail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_company_home_detail,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imageCompany?.setImageResource(ds[position].imgcompany)
            txtNameCompany?.text = ds[position].tencongty
            txtTrangThaiCtyCompany?.text = "Trạng Thái: ${ds[position].trangthaicty}$"
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}