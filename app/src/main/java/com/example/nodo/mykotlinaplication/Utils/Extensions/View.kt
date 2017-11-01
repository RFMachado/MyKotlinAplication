package com.example.nodo.mykotlinaplication.Utils.Extensions

import android.view.View

/**
 * Created by nodo on 31/10/17.
 */
fun View.visible(visible: Boolean = false) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}
