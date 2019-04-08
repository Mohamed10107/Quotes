package com.coderbot.quotes.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

class Keyboard
{
    fun show(activity: Activity, view: View)
    {
        val keyboard = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (!keyboard.isActive)
        {
            keyboard.showSoftInput(view, 0)
        }
    }

    fun hide(activity: Activity, view: View)
    {
        val keyboard = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(view.windowToken, 0)
    }
}