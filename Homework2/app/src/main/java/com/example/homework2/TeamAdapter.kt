package com.example.homework2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.homework2.databinding.ItemTeamBinding

class TeamAdapter(
    private val list: List<Team>,
    private val glide : RequestManager,
    private val onClick : (Team) -> Unit,
) : RecyclerView.Adapter<TeamHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        return TeamHolder(
            ItemTeamBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide= glide,
            onClick = onClick,

        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.onBind(list[position])
    }
}