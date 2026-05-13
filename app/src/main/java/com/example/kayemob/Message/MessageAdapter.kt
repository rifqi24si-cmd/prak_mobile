package com.example.kayemob.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kayemob.R

class MessageAdapter(
    context: Context,
    private val resource: Int,
    private val objects: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val avatarImg = view.findViewById<ImageView>(R.id.avatarImg)
        val textSender = view.findViewById<TextView>(R.id.textSender)
        val textMessage = view.findViewById<TextView>(R.id.textMessage)

        val item = objects[position]

        textSender.text = item.senderName
        textMessage.text = item.messageText

        Glide.with(context)
            .load(item.avatarUrl)
            .circleCrop()
            .into(avatarImg)

        return view
    }
}