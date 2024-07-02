package com.example.homework2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.homework2.HiddenTextFragment.Companion.ARG_NAME
import com.example.homework2.databinding.HiddenTextFragmentBinding

class HiddenTextFragment : Fragment(R.layout.hidden_text_fragment) {
    var binding : HiddenTextFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HiddenTextFragmentBinding.bind(view)
        val text = arguments?.getString(ARG_NAME) ?: "ERROR"
        binding?.run{
            tvTitle.text = "Your text - $text"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    companion object{
        private const val ARG_NAME = "ARG_NAME"
        fun bundle(name:String) : Bundle = Bundle().apply {
            putString(ARG_NAME, name)
        }
    }
}