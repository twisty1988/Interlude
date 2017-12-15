package com.twisty.interlude.example

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.twisty.interlude.R
import com.twisty.interlude.lib.IndicatorType
import com.twisty.interlude.lib.Interlude

open class BaseActivity : AppCompatActivity() {
    val interlude: Interlude by lazy {
        Interlude().apply {
            dim = 0.1F
            indicatorColorResource = android.R.color.darker_gray
            backgroundResource = R.drawable.back_ground
            indicatorType = IndicatorType.BallClipRotatePulseIndicator
            isCancelable = true
            canceledOnTouchOutside = false
            cancelCallback = {
                Log.i(localClassName, "Cancel")
            }
            dismissCallback = {
                Log.i(localClassName, "Dismiss")
            }
        }
    }
}
