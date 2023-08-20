package com.example.findjob.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findjob.R
import com.example.findjob.model.DataView

class ViewAdapterListCompany(var ds:List<DataView>, val onClick:RvInterface): RecyclerView.Adapter<ViewAdapterListCompany.Detail>() {

    var imgListCompany: ImageView?= null
    var txtListNameCompany: TextView?= null
    var txtListStatusCompany: TextView?= null
    var txtListLocationCompany: TextView?= null
    inner class Detail(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            imgListCompany = itemView.findViewById(R.id.img_list_company)
            txtListNameCompany = itemView.findViewById(R.id.txt_list_name_company)
            txtListStatusCompany = itemView.findViewById(R.id.txt_list_status_company)
            txtListLocationCompany = itemView.findViewById(R.id.txt_list_location_company)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_profile_list_company,parent,false)
        return  Detail(view)
    }

    override fun onBindViewHolder(holder: Detail, position: Int) {
        holder.itemView.apply {
            imgListCompany?.setImageResource(ds[position].imgcompany)
            txtListNameCompany?.text = ds[position].tencongty
            txtListStatusCompany?.text = "Trạng thái hoạt động: ${ds[position].trangthaicty}"
            txtListLocationCompany?.text = "Địa Chỉ: ${ds[position].diaChi},${ds[position].tinh_tp}"
            holder.itemView.setOnClickListener{
                onClick.OnClickRv(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}