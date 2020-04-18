package com.highestaim.coronavirusinfo.util

import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.coronavirusinfo.R


fun EditText.clearText() {
    this.setText("")
}


fun <R> RecyclerView.initRecyclerView(context: Context?, adapter: R, isVertical: Boolean = true) {
    context?.let {
        this.layoutManager = LinearLayoutManager(
            context,
            if (isVertical) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL,
            false
        )
        this.adapter = adapter as RecyclerView.Adapter<*>
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

inline fun <T : View> T.onClick(crossinline func: T.() -> Unit) {
    setOnClickListener { func() }
}

fun Fragment.replaceFragment(fragment: Fragment, addBackStack: Boolean) {
    if (addBackStack) {
        activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
            ?.replace(R.id.fragmentContainer, fragment)
            ?.commitAllowingStateLoss()
    } else {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, fragment)
            ?.commitAllowingStateLoss()
    }
}
