package com.example.myapplication.viewholder

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class FormItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    companion object {
        const val PADDING_IN_DIPS = 16
    }

    private var padding: Int = 0

    init {
        val metrics = context.resources.displayMetrics
        padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PADDING_IN_DIPS.toFloat(), metrics).toInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }
        outRect.bottom = padding
    }
}