package com.task.toshiba.multichoicesquizapp.Common

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.annotation.Dimension
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemOffestDecoration(private var itemOffset: Int) : RecyclerView.ItemDecoration() {
    constructor(context: Context, @DimenRes itemDimmensionId: Int) : this(context.resources.getDimensionPixelOffset(itemDimmensionId))

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect!!.set(itemOffset, itemOffset, itemOffset, itemOffset)
    }
}