package com.android.whatsapp

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.android.whatsapp.models.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class UserViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) = with(itemView) {
        countTv.isVisible = false
        timeTv.isVisible = false

        titleTv.text = user.name
        subtitleTv.text = user.status

        Picasso.get()
            .load(user.thumbImage)
            .placeholder(R.drawable.defaultavatar)
            .error( R.drawable.defaultavatar)
            .into(userImgView)
    }
}