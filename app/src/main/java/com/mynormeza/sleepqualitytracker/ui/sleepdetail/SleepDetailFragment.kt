package com.mynormeza.sleepqualitytracker.ui.sleepdetail

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
import com.mynormeza.sleepqualitytracker.databinding.SleepDetailFragmentBinding
import com.mynormeza.sleepqualitytracker.db.SleepDatabase

class SleepDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: SleepDetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.sleep_detail_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = SleepDetailFragmentArgs.fromBundle(requireArguments())

        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepDetailViewModelFactory(arguments.sleepNightKey, dataSource)

        val sleepDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(SleepDetailViewModel::class.java)

        binding.sleepDetailViewModel = sleepDetailViewModel

        binding.lifecycleOwner = this

        binding.executePendingBindings()

        sleepDetailViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
                sleepDetailViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}