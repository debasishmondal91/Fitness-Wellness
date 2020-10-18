package com.example.fitnesswellness.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.uploadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}