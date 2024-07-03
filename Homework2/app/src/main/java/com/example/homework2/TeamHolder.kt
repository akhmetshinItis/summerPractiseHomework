package com.example.homework2

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.example.homework2.databinding.ItemTeamBinding
import java.security.PrivateKey

class TeamHolder(
    private val binding : ItemTeamBinding,
    private val glide : RequestManager,
    private val onClick : (Team) -> Unit,
) : ViewHolder(binding.root) {

    fun onBind(team: Team){
        binding.run{
            tvTeam.text = team.name
            tvCountry.text = team.country

            glide
                .load(team.url)
                .error(R.drawable.not_found)
                .into(ivImage)
            root.setOnClickListener{
                onClick.invoke(team)
            }
        }
    }
}