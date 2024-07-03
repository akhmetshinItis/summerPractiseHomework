package com.example.homework2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.homework2.databinding.FragmentInfoBinding
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment(R.layout.fragment_info) {
    private var binding : FragmentInfoBinding? = null
    private var adapter : TeamAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        initAdapter()
    }

    private fun initAdapter(){
        adapter = TeamAdapter(
            list = TeamRepository.teams,
            glide = Glide.with(this),
            onClick = {
                //Snackbar.make(binding!!.root, it.url, Snackbar.LENGTH_SHORT).show()
                findNavController().navigate(
                    resId = R.id.action_infoFragment_to_teamPageFragment,
                    args = TeamPageFragment.bundle(
                        id = it.id
                    )
                )
            }
        )
        binding?.run{
            rvTeam.adapter = adapter
            rvTeam.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}