package com.entaku.football.view.adapter

import android.view.View
import androidx.databinding.BindingAdapter

object CustomBindingAdapter {
    //xmlに定義する際のBindingAdapter

    @BindingAdapter("visibleGone")
    @JvmStatic
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}
