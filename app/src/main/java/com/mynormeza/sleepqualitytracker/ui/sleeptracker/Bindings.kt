package com.mynormeza.sleepqualitytracker.ui.sleeptracker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mynormeza.sleepqualitytracker.R
import com.mynormeza.sleepqualitytracker.convertDurationToFormatted
import com.mynormeza.sleepqualitytracker.convertNumericQualityToString
import com.mynormeza.sleepqualitytracker.db.SleepNight

@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: SleepNight) {
    setImageResource(when (item.sleepQuality) {
        0 -> R.drawable.ic_sleep_0
        1 -> R.drawable.ic_sleep_1
        2 -> R.drawable.ic_sleep_2
        3 -> R.drawable.ic_sleep_3
        4 -> R.drawable.ic_sleep_4
        5 -> R.drawable.ic_sleep_5
        else -> R.drawable.ic_sleep_active
    })
}

@BindingAdapter("sleepDurationFormatted")
fun TextView.setSleepDurationFormatted(item: SleepNight?) {
    item?.let {
        text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli,context.resources)
    }
}

@BindingAdapter("sleepQualityString")
fun TextView.setSleepQualityString(item: SleepNight?) {
    item?.let {
        text = convertNumericQualityToString(item.sleepQuality, context.resources)
    }
}