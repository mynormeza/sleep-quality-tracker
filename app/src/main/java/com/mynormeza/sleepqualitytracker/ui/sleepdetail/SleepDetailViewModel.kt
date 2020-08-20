package com.mynormeza.sleepqualitytracker.ui.sleepdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mynormeza.sleepqualitytracker.db.SleepDatabaseDao
import com.mynormeza.sleepqualitytracker.db.SleepNight
import kotlinx.coroutines.Job

class SleepDetailViewModel(
    private val sleepNightKey: Long = 0L,
    dataSource: SleepDatabaseDao
) : ViewModel() {

    val database = dataSource
    private val viewModelJob = Job()

    private val night = MediatorLiveData<SleepNight>()

    fun getNight() = night

    init {
        val id = database.getNightWithId(sleepNightKey)
        night.addSource(id, night::setValue)
    }

    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    fun onClose() {
        _navigateToSleepTracker.value = true
    }

}
