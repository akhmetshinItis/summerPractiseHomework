package com.example.homework2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homework2.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(R.layout.fragment_main) {
    private var binding : FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding?.run {
            sendButton.setOnClickListener{
                val name = etProfileName.text.toString()
                if(name.isEmpty()){
                    Snackbar.make(root, "Для отправки текста требуется заполнить поле", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    findNavController().navigate(
                        resId = R.id.action_mainFragment_to_hiddenTextFragment,
                        args = HiddenTextFragment.bundle(
                            name = etProfileName.text.toString()
                        )
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}