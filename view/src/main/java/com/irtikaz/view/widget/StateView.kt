package com.irtikaz.view.widget

import android.graphics.drawable.Drawable
import android.view.View

interface StateView {
    fun showContent()
    fun showError()
    fun showError(title: String, message: String, buttonText: String?, listener: View.OnClickListener?)
    fun showErrorWithImage(img: Drawable, title: String, message: String, buttonText: String?, listener: View.OnClickListener?)
    fun showLoading()
    fun currentState(): String
}