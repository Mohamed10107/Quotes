package com.coderbot.quotes.utils

import android.content.res.Resources
import android.util.DisplayMetrics

class Dimensions
{
    fun dp_to_px(dp: Int): Int
    {
        return Math.round(dp * (Resources.getSystem().displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun px_to_dp(px: Int): Int
    {
        return Math.round(px / (Resources.getSystem().displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }
}