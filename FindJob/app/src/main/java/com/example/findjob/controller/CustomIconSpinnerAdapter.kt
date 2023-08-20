package com.example.findjob.controller

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.findjob.R

class CustomIconSpinnerAdapter (
    context: Context,
    private val items: List<String>,
    private val icons: Int
) : ArrayAdapter<String>(context, R.layout.layout_txt_sp_icon, items) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val iconImageView = view.findViewById<ImageView>(R.id.txt_sp_item)
        val textView = view.findViewById<TextView>(R.id.txt_sp_item)

        iconImageView.setImageResource(icons)
        textView.text = items[position]
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}