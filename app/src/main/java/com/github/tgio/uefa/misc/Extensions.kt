package com.github.tgio.uefa.misc

import android.content.res.Resources

fun Number.toDp(): Float = (this.toFloat() * Resources.getSystem().displayMetrics.density)
