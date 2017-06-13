package com.twisty.interlude.lib

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twisty.lib.R
import com.wang.avi.AVLoadingIndicatorView


/**
 * Project : InterludeApp<br>
 * Created by twisty on 2017/6/12.<br>
 *
 */
class Interlude(val fm: FragmentManager) : DialogFragment() {

    var customIndicator: String? = null
    var indicatorType: IndicatorType = IndicatorType.BallPulseIndicator
    var backGroundColorResource: Int = android.R.color.transparent
    var indicatorColorResource: Int = R.color.defaultIndicatorColor
    var cancelCallback: ((dialog: DialogInterface?) -> Unit)? = null
    var dismissCallback: ((dialog: DialogInterface?) -> Unit)? = null
    var dim: Float = 0.1F
    var canceledOnTouchOutside: Boolean = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawableResource(backGroundColorResource)
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside)
        val view = inflater?.inflate(R.layout.loading_indicator, container, false)
        val indicator = view?.findViewById(R.id.progressBar) as AVLoadingIndicatorView
        indicator.setIndicatorColor(resources.getColor(indicatorColorResource))
        indicator.setIndicator(customIndicator ?: indicatorType.name)
        return view
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        cancelCallback?.invoke(dialog)
    }


    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        dismissCallback?.invoke(dialog)
    }

    override fun onStart() {
        super.onStart()
        val window = dialog.window
        val windowParams = window.attributes
        windowParams.dimAmount = dim
        window.attributes = windowParams
    }


    fun show() {
        show(fm, "")
    }

}