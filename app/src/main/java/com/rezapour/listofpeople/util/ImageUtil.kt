package com.rezapour.listofpeople.util

import android.content.Context
import android.graphics.Color
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.rezapour.listofpeople.R

object ImageUtil {

    fun getAvatar(context: Context, name: String) = AvatarGenerator.AvatarBuilder(context)
        .setLabel(name)
        .setAvatarSize(60)
        .setTextSize(20)
        .toCircle()
        .setBackgroundColor(context.getColor(R.color.avatarBackGroundColor))
        .build()
}