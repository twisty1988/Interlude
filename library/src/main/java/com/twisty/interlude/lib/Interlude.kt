package com.twisty.interlude.lib

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.twisty.lib.R
import com.wang.avi.AVLoadingIndicatorView


/**
 * Project : InterludeApp<br>
 * Created by twisty on 2017/6/12.<br>
 *
 */
class Interlude : DialogFragment() {


    private var customIndicator: String? = null
    var indicatorType: IndicatorType = IndicatorType.LineScalePulseOutIndicator
    var backgroundResource: Int = android.R.color.transparent
    var indicatorColorResource: Int = R.color.defaultIndicatorColor
    var cancelCallback: ((dialog: DialogInterface?) -> Unit)? = null
    var dismissCallback: ((dialog: DialogInterface?) -> Unit)? = null
    var dim: Float = 0.3F
    var canceledOnTouchOutside: Boolean = true


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(backgroundResource)
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

    fun isShowing(): Boolean = this.isAdded && this.dialog != null && this.dialog.isShowing


    fun show(fm: FragmentManager) {
        show(fm, "Interlude")
    }

    override fun show(manager: FragmentManager, tag: String) {
        try {
            val ft = manager.beginTransaction()
            if (!this.isAdded) ft.remove(this)
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}