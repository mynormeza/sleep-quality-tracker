package com.mynormeza.sleepqualitytracker.ui.sleepquality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mynormeza.sleepqualitytracker.R
import com.mynormeza.sleepqualitytracker.databinding.SleepQualityFragmentBinding
import com.mynormeza.sleepqualitytracker.db.SleepDatabase

class SleepQualityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: SleepQualityFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.sleep_quality_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource)

        val sleepQualityViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(SleepQualityViewModel::class.java)

        binding.sleepQualityViewModel = sleepQualityViewModel

        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())

                sleepQualityViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}