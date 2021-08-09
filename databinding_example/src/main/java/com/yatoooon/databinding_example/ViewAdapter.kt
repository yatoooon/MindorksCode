package com.yatoooon.databinding_example

import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion


object ViewAdapter {
    @JvmStatic
    @BindingAdapter("android:text")  //属性预处理
    fun setText(view: TextView, text: CharSequence?) {
        view.text = text.toString().toLowerCase()
    }

//    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)  //自定义属性
//    fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
//        if (url == null) {
//            imageView.setImageDrawable(placeholder);
//        } else {
//            MyImageLoader.loadInto(imageView, url, placeholder);
//        }
//    }

    @JvmStatic
    @BindingConversion  //类型转换
    fun colorToDrawable(color: Int): ColorDrawable? {
        return ColorDrawable(color)
    }


}