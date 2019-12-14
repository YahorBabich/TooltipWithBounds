package com.wsc.tooltip

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.StringRes

class TooltipWindow(val context: Context) {
    private var tipWindow: PopupWindow = PopupWindow(context)
    private val contentView: View

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
        tipWindow.setBackgroundDrawable(
            BitmapDrawable(
                context.resources, Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
            )
        )
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
            x = root.width - width + lRoot[0]
        }

        tipWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, x, y)

        Handler().postDelayed({
            tipWindow.dismiss()
        }, DELAY_MILLIS)
    }

    companion object {
        private const val DELAY_MILLIS = 2000L
    }
}
