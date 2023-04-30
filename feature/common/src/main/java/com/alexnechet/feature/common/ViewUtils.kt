package com.alexnechet.feature.common

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation

fun ImageView.loadImage(context: Context, url: String, isCircleShape: Boolean = false) {
    this.load(
        data = url,
        imageLoader = context.createImageLoader()
    ) {
        crossfade(true)
        placeholder(R.drawable.baseline_broken_image_24)
        if (isCircleShape) {
            transformations(CircleCropTransformation())
        }
    }
}


fun Context.createImageLoader() = ImageLoader.Builder(this)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .diskCachePolicy(CachePolicy.ENABLED)
    .build()


fun View.isVisible(shouldShow: Boolean) {
    this.visibility = if (shouldShow) View.VISIBLE else View.GONE
}
