package com.example.homework2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.homework2.databinding.FragmentSupportBinding

class SupportFragment : Fragment(R.layout.fragment_support) {
    private var binding : FragmentSupportBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSupportBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}