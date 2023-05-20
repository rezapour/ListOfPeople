package com.rezapour.listofpeople.util

import android.content.Context
import android.graphics.Color
import com.avatarfirst.avatargenlib.AvatarGenerator

object ImageUtil {

    fun getAvatar(context: Context, name: String) = AvatarGenerator.AvatarBuilder(context)
        .setLabel(name)
        .setAvatarSize(65)
        .setTextSize(20)
        .toCircle()
        .setBackgroundColor(Color.GRAY)
        .build()
}