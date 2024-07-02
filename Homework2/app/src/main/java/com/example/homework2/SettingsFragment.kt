package com.example.homework2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.homework2.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var binding : FragmentSettingsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        binding.run {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}