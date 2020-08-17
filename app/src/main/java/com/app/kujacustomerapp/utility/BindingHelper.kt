package com.app.kujacustomerapp.utility

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.kujacustomerapp.R
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import de.hdodenhof.circleimageview.CircleImageView

object BindingHelper {

    @JvmStatic
    @BindingAdapter("errorText")
    fun errorText(view: TextInputLayout, errorMessage: Int?) {
        view.error = errorMessage?.let { view.context.getString(it) }
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: CircleImageView, url: String?) {
        Glide.with(view.context)
            .load(url).placeholder(R.drawable.ic_account)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("loadImageInIv")
    fun loadImageInIv(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url).placeholder(R.drawable.ic_account)
            .into(view)
    }

}