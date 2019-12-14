package com.wsc.tooltip

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.content.Context
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView

class TooltipWindow(private val ctx: Context) {
    private lateinit var tipWindow: PopupWindow
    private val contentView: View
    private val inflater: LayoutInflater

    internal var handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            when (msg.what) {
                MSG_DISMISS_TOOLTIP -> if (tipWindow.isShowing)
                    tipWindow.dismiss()
            }
        }
    }

    init {
        tipWindow = PopupWindow(ctx)

        inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        contentView = inflater.inflate(R.layout.tooltip_layout, null)
    }

    fun showToolTip(anchor: View, parent: View) {
        tipWindow.height = LayoutParams.WRAP_CONTENT
        tipWindow.width = LayoutParams.WRAP_CONTENT
        tipWindow.isOutsideTouchable = true
        tipWindow.isTouchable = true
        tipWindow.isFocusable = true
        //   tipWindow.setBackgroundDrawable(BitmapDrawable())
        tipWindow.contentView = contentView

        val text = contentView.findViewById<TextView>(R.id.tooltip_text)
        text.maxWidth = parent.width

        val locations = IntArray(2)
        anchor.getLocationOnScreen(locations)

        contentView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        val contentViewHeight = contentView.measuredHeight
        val contentViewWidth = contentView.measuredWidth

        tipWindow.showAtLocation(anchor, Gravity.NO_GRAVITY,
            locations[0] + anchor.width / 2 - contentViewWidth / 2,
            locations[1] - contentViewHeight)

        handler.sendEmptyMessageDelayed(MSG_DISMISS_TOOLTIP, 2000)
    }

    companion object {

        private val MSG_DISMISS_TOOLTIP = 100
    }
}
