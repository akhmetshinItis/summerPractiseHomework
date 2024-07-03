package com.example.homework2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.homework2.databinding.FragmentTeamPageBinding

class TeamPageFragment : Fragment(R.layout.fragment_team_page) {
    private var binding : FragmentTeamPageBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeamPageBinding.bind(view)
        val id = arguments?.getInt(ARG_NAME) ?: 0
        val team : Team? = TeamRepository.teams.find {
            it.id == id
        }
        binding?.run {

            initViews(view, team)
            ivBack.setOnClickListener{
                findNavController().navigate(R.id.action_teamPageFragment_to_infoFragment)
            }
        }
    }

    private fun FragmentTeamPageBinding.initViews(
        view: View,
        team: Team?
    ) {
        Glide.with(view)
            .load(team?.url)
            .error(R.drawable.not_found)
            .into(ivTeamImg)

        tvTeam.text = team?.name
        tvCountry.text = team?.country
        tvShortDescription.text = "Краткое описание: ${team?.shortDescription}"
        tvLongDescription.text = "Подробное описание: ${team?.longDescription}"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object{
        private const val ARG_NAME = "ARG_NAME"
        fun bundle(id:Int) : Bundle = Bundle().apply {
            putInt(ARG_NAME, id)
        }
    }
}