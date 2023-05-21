package com.rezapour.listofpeople.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.bumptech.glide.Glide
import com.rezapour.listofpeople.R

object ImageUtil {
    fun getAvatar(context: Context, name: String) = AvatarGenerator.AvatarBuilder(context)
        .setLabel(name)
        .setAvatarSize(60)
        .setTextSize(20)
        .toCircle()
        .setBackgroundColor(context.getColor(R.color.avatarBackGroundColor))
        .build()

    fun loadImage(context: Context, url: String?, error: Drawable, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .error(error)
            .circleCrop()
            .into(imageView)
    }
}