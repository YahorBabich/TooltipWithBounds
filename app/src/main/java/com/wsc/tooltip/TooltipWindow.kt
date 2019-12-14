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
import androidx.annotation.StringRes

class TooltipWindow(context: Context) {
    private var tipWindow: PopupWindow = PopupWindow(context)
    private val contentView: View

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
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        contentView = inflater.inflate(R.layout.tooltip_layout, null)
    }

    fun showToolTip(anchor: View, @StringRes resid: Int, root: View) {
        tipWindow.height = LayoutParams.WRAP_CONTENT
        tipWindow.width = LayoutParams.WRAP_CONTENT
        tipWindow.isOutsideTouchable = true
        tipWindow.isTouchable = true
        tipWindow.isFocusable = true
        //   tipWindow.setBackgroundDrawable(BitmapDrawable())
        tipWindow.contentView = contentView

        val text = contentView.findViewById<TextView>(R.id.tooltip_text)
        text.setText(resid)
        text.maxWidth = root.width

        val lAnchor = IntArray(2)
        anchor.getLocationOnScreen(lAnchor)

        val lRoot = IntArray(2)
        root.getLocationOnScreen(lRoot)

        contentView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        val height = contentView.measuredHeight
        val width = contentView.measuredWidth

        var x = lAnchor[0] + anchor.width / 2 - width / 2
        val y = lAnchor[1] - height

        if (x < lRoot[0]) { // left
            x = lRoot[0]
        }

        if (x + width > lRoot[0] + root.width) { // right
            x = lRoot[0]
        }


        tipWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, x, y)
        handler.sendEmptyMessageDelayed(MSG_DISMISS_TOOLTIP, 2000)
    }

    companion object {
        private val MSG_DISMISS_TOOLTIP = 100
    }
}
