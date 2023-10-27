package com.stone.news.utils

import android.content.Context
import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat

object PrettyTimeAgo {
    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS
    fun getTimeAgo(time: Long): String? {
        var time = time
        val now = System.currentTimeMillis()
        if (time < 1000000000000L) {
            time *= 1000
        }
        if (time > now || time <= 0) {
            return null
        }
        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "just now"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "a minute ago"
        } else if (diff < 50 * MINUTE_MILLIS) {
            (diff / MINUTE_MILLIS).toString() + " minutes ago"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "an hour ago"
        } else if (diff < 24 * HOUR_MILLIS) {
            (diff / HOUR_MILLIS).toString() + " hours ago"
        } else if (diff < 7 * DAY_MILLIS) {
            if ((diff / DAY_MILLIS).toString() == "1") {
                "1 day ago"
            } else {
                (diff / DAY_MILLIS).toString() + " days ago"
            }
        } else if (diff < 4 * DateUtils.WEEK_IN_MILLIS) {
            if ((diff / DateUtils.WEEK_IN_MILLIS).toString() == "1") {
                "1 week ago"
            } else {
                (diff / DateUtils.WEEK_IN_MILLIS).toString() + " week ago"
            }
        } else {
            "More than a month ago"
        }
    }

    @Throws(ParseException::class)
    fun getTimeAgo(context: Context, timeString: String?, simpleDateFormat: String?): String? {
        val now = System.currentTimeMillis()
        var time = timestampToMilli(context, timeString, simpleDateFormat)
        if (time < 1000000000000L) {
            time *= 1000
        }
        if (time > now || time <= 0) {
            return null
        }
        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "just now"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "a minute ago"
        } else if (diff < 50 * MINUTE_MILLIS) {
            (diff / MINUTE_MILLIS).toString() + " minutes ago"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "an hour ago"
        } else if (diff < 24 * HOUR_MILLIS) {
            (diff / HOUR_MILLIS).toString() + " hours ago"
        } else if (diff < 7 * DAY_MILLIS) {
            if ((diff / DAY_MILLIS).toString() == "1") {
                "1 day ago"
            } else {
                (diff / DAY_MILLIS).toString() + " days ago"
            }
        } else if (diff < 4 * DateUtils.WEEK_IN_MILLIS) {
            if ((diff / DateUtils.WEEK_IN_MILLIS).toString() == "1") {
                "1 week ago"
            } else {
                (diff / DateUtils.WEEK_IN_MILLIS).toString() + " week ago"
            }
        } else {
            "More than a month ago"
        }
    }

    @Throws(ParseException::class)
    fun timestampToMilli(context: Context, timestamp: String?, simpleDateFormat: String?): Long {
        val desiredFormat = SimpleDateFormat(
            simpleDateFormat,
            context.resources.configuration.locale
        )
        val date = desiredFormat.parse(timestamp)!!
        return date.time
    }
}