package com.afifny.mystackwidget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf

internal class StackRemoteViewsFactory(private val mContext: Context) : RemoteViewsService.RemoteViewsFactory {
    private val mWidgetItem = ArrayList<Bitmap>()
    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        mWidgetItem.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.darth_vader))
        mWidgetItem.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.star_wars_logo))
        mWidgetItem.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.storm_trooper))
        mWidgetItem.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.starwars))
        mWidgetItem.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.falcon))
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int {
        return mWidgetItem.size
    }

    override fun getViewAt(p0: Int): RemoteViews {
        val rv= RemoteViews(mContext.packageName, R.layout.widget_item)
        rv.setImageViewBitmap(R.id.imageView, mWidgetItem[p0])

        val extras = bundleOf(
            ImageBannerWidget.EXTRA_ITEM to p0
        )
        val fillItent = Intent()
        fillItent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillItent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(p0: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}