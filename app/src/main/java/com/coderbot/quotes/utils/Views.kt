package com.coderbot.quotes.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.coderbot.quotes.view.loading.LoadingDialog

class Views
{
    class LoadingView(context: Context)
    {
        private val loading: LoadingDialog = LoadingDialog(context)

        fun show()
        {
            if (!loading.isShowing)
            {
                loading.show()
            }
        }

        fun dismiss()
        {
            if (loading.isShowing)
            {
                loading.dismiss()
            }
        }
    }

    object ImageLoader
    {
        fun load(context: Context, imageView: ImageView, imageURl: String, auth: String)
        {
            glideLoader(context, imageView, GlideUrl(imageURl, LazyHeaders.Builder().addHeader("Authorization", auth).build()))
        }

        fun glideLoader(context: Context, imageView: ImageView, imagePath: String)
        {
            Glide.with(context).load(imagePath).thumbnail(0.1f).into(imageView)
        }

        private fun glideLoader(context: Context, imageView: ImageView, uri: GlideUrl)
        {
            Glide.with(context).load(uri).thumbnail(0.1f).into(imageView)
        }

        fun load(context: Context, imageURl: String, auth: String, imageView: ImageView, placeHolder: Drawable)
        {
            Glide.with(context).load(GlideUrl(imageURl, LazyHeaders.Builder().addHeader("Authorization", auth).build())).thumbnail(0.1f).apply(RequestOptions().placeholder(placeHolder)).into(imageView)
        }

        fun load(context: Context, imagePath: String, imageView: ImageView, placeHolder: Drawable)
        {
            Glide.with(context).load(imagePath).thumbnail(0.1f).apply(RequestOptions().placeholder(placeHolder)).into(imageView)
        }
    }
}