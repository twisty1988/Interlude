package com.twisty.interlude.example

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.twisty.interlude.R
import com.twisty.interlude.lib.IndicatorType
import com.twisty.interlude.lib.Interlude
import org.jetbrains.anko.AnkoLogger

class MainActivity : AppCompatActivity(), AnkoLogger {
    var interlude: Interlude = Interlude(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(interlude) {
            dim = 0.1F
            indicatorColorResource = android.R.color.darker_gray
            backGroundColorResource = android.R.color.white
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
        setContentView(R.layout.activity_main)
        findViewById(R.id.view).setOnClickListener {
            interlude.show()
            Handler().postDelayed({
                interlude.dismiss()
            }, 5000)
        }
    }
}
