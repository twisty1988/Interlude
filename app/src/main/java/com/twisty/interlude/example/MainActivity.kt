package com.twisty.interlude.example

import android.os.Bundle
import android.os.Handler
import com.twisty.interlude.R
import org.jetbrains.anko.AnkoLogger

class MainActivity : BaseActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.view).setOnClickListener {
            interlude.show(supportFragmentManager)
            Handler().postDelayed({
                if (interlude.isShowing()) interlude.dismiss()
            }, 5000)
        }
    }
}
