package com.example.kayemob.More

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kayemob.R

class CustomAdapter(
    context: Context,
    private val resource: Int,
    private val objects: List<MessageItem>
) : ArrayAdapter<MessageItem>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(context)
        val view = convertView ?: layoutInflater.inflate(resource, parent, false)

        val imgItem = view.findViewById<ImageView>(R.id.imgItem)
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtDesc = view.findViewById<TextView>(R.id.txtDesc)

        val item = objects[position]

        txtTitle.text = item.title
        txtDesc.text = item.desc

        // Menggunakan Glide untuk memuat gambar dari URL
        Glide.with(context)
            .load(item.imageUrl)
            .circleCrop()
            .into(imgItem)

        return view
    }
}